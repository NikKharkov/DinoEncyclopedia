package com.example.dinoencyclopedia.ui.map

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.dinoencyclopedia.R
import com.example.dinoencyclopedia.data.local.DinoLocationsService
import com.example.dinoencyclopedia.databinding.FragmentMapBinding
import com.example.dinoencyclopedia.domain.map.DinoSticker
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraBoundsOptions
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.CoordinateBounds
import com.mapbox.maps.RenderedQueryGeometry
import com.mapbox.maps.RenderedQueryOptions
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.dsl.generated.get
import com.mapbox.maps.extension.style.expressions.dsl.generated.literal
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.concat
import com.mapbox.maps.extension.style.layers.addLayer
import com.mapbox.maps.extension.style.layers.generated.symbolLayer
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor
import com.mapbox.maps.extension.style.layers.properties.generated.ProjectionName
import com.mapbox.maps.extension.style.projection.generated.Projection
import com.mapbox.maps.extension.style.projection.generated.setProjection
import com.mapbox.maps.extension.style.sources.addSource
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.plugin.gestures.addOnMapClickListener

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomSheet()
        setupMap()
    }

    private fun setupBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.peekHeight = 0
        bottomSheetBehavior.isHideable = true

        binding.bottomSheet.visibility = View.GONE
    }


    private fun setupMap() {
        binding.dinoMap.mapboxMap.getStyle {
            binding.dinoMap.mapboxMap.loadStyle("mapbox://styles/nikkharkov/cmhpe8dcu000k01s67tbj2n8s") { style ->

                style.setProjection(Projection(ProjectionName.MERCATOR))

                binding.dinoMap.mapboxMap.setBounds(
                    CameraBoundsOptions.Builder()
                        .bounds(
                            CoordinateBounds(
                                Point.fromLngLat(-180.0, -85.0),
                                Point.fromLngLat(180.0, 85.0),
                                false
                            )
                        )
                        .build()
                )

                addDinosaurStickers(style)
                addLocationMarkers(style)
                setupMapClickListener()

                binding.dinoMap.mapboxMap.setCamera(
                    CameraOptions.Builder()
                        .center(Point.fromLngLat(-98.0, 39.5))
                        .pitch(0.0)
                        .zoom(4.0)
                        .bearing(0.0)
                        .build()
                )
            }
        }
    }

    private fun addLocationMarkers(style: Style) {
        val locations = DinoLocationsService.getAllLocations()

        val markerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_map_mark)
        style.addImage("location-marker", markerBitmap)

        val features = locations.map { location ->
            Feature.fromGeometry(Point.fromLngLat(location.longitude, location.latitude))
                .apply { addStringProperty("location_id", location.id.toString()) }
        }

        val source = geoJsonSource("locations-source") {
            featureCollection(FeatureCollection.fromFeatures(features))
        }

        style.addSource(source)

        val layer = symbolLayer("locations-layer", "locations-source") {
            iconImage("location-marker")
            iconSize(0.25)
            iconAllowOverlap(true)
            iconAnchor(IconAnchor.BOTTOM)
        }
        style.addLayer(layer)
    }

    private fun setupMapClickListener() {
        binding.dinoMap.mapboxMap.addOnMapClickListener { point ->
            val screenPoint = binding.dinoMap.mapboxMap.pixelForCoordinate(point)

            binding.dinoMap.mapboxMap.queryRenderedFeatures(
                RenderedQueryGeometry(screenPoint),
                RenderedQueryOptions(listOf("locations-layer"), null)
            ) { expected ->
                expected.value?.firstOrNull()?.let { feature ->
                    val locationId = feature.queriedFeature.feature.properties()?.get("location_id")?.asString
                    locationId?.let { showLocationInfo(it.toInt()) }
                }
            }
            true
        }
    }

    private fun showLocationInfo(locationId: Int) {
        val location = DinoLocationsService.getLocationById(locationId)!!

        binding.locationDinoImage.setImageResource(location.imageRes)
        binding.locationDinoTitle.text = location.title
        binding.locationDinoDiscoveryYear.text = location.discoveryYear.toString()
        binding.locationDinoDescription.text = resources.getString(location.description)

        binding.bottomSheet.visibility = View.VISIBLE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun addDinosaurStickers(style: Style) {
        val dinosaurs = listOf(
            DinoSticker("mosasaurus", R.drawable.sticker_mosasaur, -40.0, 30.0),
            DinoSticker("diplodocus", R.drawable.sticker_diplodocus, -69.0, -41.0),
            DinoSticker("trex", R.drawable.sticker_trex, -104.0, 46.0),
            DinoSticker("spinosaurus", R.drawable.sticker_spinosaurus, 30.0, 28.0),
            DinoSticker("iguanodon", R.drawable.sticker_iguanodon, 4.0, 50.0),
            DinoSticker("velociraptor", R.drawable.sticker_velociraptor, 103.0, 43.0)
        )

        dinosaurs.forEach { dino ->
            val bitmap = BitmapFactory.decodeResource(resources, dino.iconRes)
            style.addImage("${dino.id}-sticker", bitmap)
        }

        val features = dinosaurs.map { dino ->
            Feature.fromGeometry(Point.fromLngLat(dino.longitude, dino.latitude))
                .apply { addStringProperty("icon_id", dino.id) }
        }

        val source = geoJsonSource("dino-stickers-source") {
            featureCollection(FeatureCollection.fromFeatures(features))
        }
        style.addSource(source)

        val layer = symbolLayer("dino-stickers-layer", "dino-stickers-source") {
            iconImage(concat(literal(""), get("icon_id"), literal("-sticker")))
            iconSize(0.35)
            iconAnchor(IconAnchor.CENTER)
            iconAllowOverlap(true)
            iconIgnorePlacement(true)
        }
        style.addLayer(layer)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}