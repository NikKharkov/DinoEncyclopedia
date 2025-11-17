package com.example.dinoencyclopedia.data.local

import com.example.dinoencyclopedia.R
import com.example.dinoencyclopedia.domain.map.DinoLocation

object DinoLocationsService {

    fun getAllLocations(): List<DinoLocation> = listOf(

        DinoLocation(
            id = 1,
            title = "Hell Creek Formation",
            imageRes = R.drawable.ic_hell_creek,
            description = R.string.desc_hell_creek,
            discoveryYear = 1902,
            latitude = 47.5,
            longitude = -106.5
        ),

        DinoLocation(
            id = 2,
            title = "Morrison Formation",
            imageRes = R.drawable.ic_morrison,
            description = R.string.desc_morrison,
            discoveryYear = 1877,
            latitude = 39.0,
            longitude = -108.5
        ),

        DinoLocation(
            id = 3,
            title = "Two Medicine Formation",
            imageRes = R.drawable.ic_two_medicine,
            description = R.string.desc_two_medicine,
            discoveryYear = 1978,
            latitude = 48.5,
            longitude = -112.5
        ),

        DinoLocation(
            id = 4,
            title = "Judith River Formation",
            imageRes = R.drawable.ic_judith_river,
            description = R.string.desc_judith_river,
            discoveryYear = 1855,
            latitude = 48.0,
            longitude = -110.0
        ),

        DinoLocation(
            id = 5,
            title = "Niobrara Chalk (Western Interior Seaway)",
            imageRes = R.drawable.ic_niobrara_sea,
            description = R.string.desc_niobrara_sea,
            discoveryYear = 1870,
            latitude = 39.5,
            longitude = -101.0
        ),

        DinoLocation(
            id = 6,
            title = "Dinosaur Provincial Park",
            imageRes = R.drawable.ic_dinosaur_provincial_park,
            description = R.string.desc_dinosaur_provincial_park,
            discoveryYear = 1884,
            latitude = 50.7,
            longitude = -111.5
        ),

        DinoLocation(
            id = 7,
            title = "Horseshoe Canyon / Drumheller",
            imageRes = R.drawable.ic_drumheller,
            description = R.string.desc_drumheller,
            discoveryYear = 1884,
            latitude = 51.5,
            longitude = -112.7
        ),

        DinoLocation(
            id = 8,
            title = "Huincul Formation (Patagonia)",
            imageRes = R.drawable.ic_huincul,
            description = R.string.desc_huincul,
            discoveryYear = 1995,
            latitude = -38.9,
            longitude = -68.1
        ),

        DinoLocation(
            id = 9,
            title = "La Amarga Formation (Patagonia)",
            imageRes = R.drawable.ic_la_amarga,
            description = R.string.desc_la_amarga,
            discoveryYear = 1984,
            latitude = -39.5,
            longitude = -70.0
        ),

        DinoLocation(
            id = 10,
            title = "Atacama Desert",
            imageRes = R.drawable.ic_atacama,
            description = R.string.desc_atacama,
            discoveryYear = 1977,
            latitude = -24.5,
            longitude = -69.2
        ),

        DinoLocation(
            id = 11,
            title = "Araripe Basin",
            imageRes = R.drawable.ic_araripe_basin,
            description = R.string.desc_araripe_basin,
            discoveryYear = 1870,
            latitude = -7.2,
            longitude = -39.5
        ),

        DinoLocation(
            id = 12,
            title = "Villa de Leyva",
            imageRes = R.drawable.ic_villa_de_leyva,
            description = R.string.desc_villa_de_leyva,
            discoveryYear = 1945,
            latitude = 5.6,
            longitude = -73.5
        ),

        DinoLocation(
            id = 13,
            title = "Lyme Regis",
            imageRes = R.drawable.ic_lyme_regis,
            description = R.string.desc_lyme_regis,
            discoveryYear = 1811,
            latitude = 50.7,
            longitude = -2.9
        ),

        DinoLocation(
            id = 14,
            title = "Isle of Wight",
            imageRes = R.drawable.ic_isle_of_wight,
            description = R.string.desc_isle_of_wight,
            discoveryYear = 1820,
            latitude = 50.7,
            longitude = -1.3
        ),

        DinoLocation(
            id = 15,
            title = "Solnhofen Limestone",
            imageRes = R.drawable.ic_solnhofen,
            description = R.string.desc_solnhofen,
            discoveryYear = 1861,
            latitude = 48.9,
            longitude = 10.9
        ),

        DinoLocation(
            id = 16,
            title = "Transylvanian Basin",
            imageRes = R.drawable.ic_transylvanian_basin,
            description = R.string.desc_transylvanian_basin,
            discoveryYear = 1895,
            latitude = 46.2,
            longitude = 24.8
        ),

        DinoLocation(
            id = 17,
            title = "Monte San Giorgio",
            imageRes = R.drawable.ic_monte_san_giorgio,
            description = R.string.desc_monte_san_giorgio,
            discoveryYear = 1924,
            latitude = 45.9,
            longitude = 8.9
        ),

        DinoLocation(
            id = 18,
            title = "Flaming Cliffs",
            imageRes = R.drawable.ic_flaming_cliffs,
            description = R.string.desc_flaming_cliffs,
            discoveryYear = 1923,
            latitude = 44.1,
            longitude = 103.7
        ),

        DinoLocation(
            id = 19,
            title = "Nemegt Basin",
            imageRes = R.drawable.ic_nemegt_basin,
            description = R.string.desc_nemegt_basin,
            discoveryYear = 1946,
            latitude = 43.5,
            longitude = 100.2
        ),

        DinoLocation(
            id = 20,
            title = "Yixian Formation",
            imageRes = R.drawable.ic_yixian_formation,
            description = R.string.desc_yixian_formation,
            discoveryYear = 1996,
            latitude = 41.2,
            longitude = 120.7
        ),

        DinoLocation(
            id = 21,
            title = "Sichuan Basin",
            imageRes = R.drawable.ic_sichuan_basin,
            description = R.string.desc_sichuan_basin,
            discoveryYear = 1958,
            latitude = 30.6,
            longitude = 104.1
        ),

        DinoLocation(
            id = 22,
            title = "Guizhou Marine Beds",
            imageRes = R.drawable.ic_guizhou_marine_beds,
            description = R.string.desc_guizhou_marine_beds,
            discoveryYear = 1935,
            latitude = 26.6,
            longitude = 106.7
        ),

        DinoLocation(
            id = 23,
            title = "Kyzylkum Desert",
            imageRes = R.drawable.ic_kyzylkum_desert,
            description = R.string.desc_kyzylkum_desert,
            discoveryYear = 1948,
            latitude = 42.5,
            longitude = 63.0
        ),

        DinoLocation(
            id = 24,
            title = "Kem Kem Beds",
            imageRes = R.drawable.ic_kem_kem_beds,
            description = R.string.desc_kem_kem_beds,
            discoveryYear = 1934,
            latitude = 31.5,
            longitude = -4.0
        ),

        DinoLocation(
            id = 25,
            title = "Oulad Abdoun Basin",
            imageRes = R.drawable.ic_oulad_abdoun_basin,
            description = R.string.desc_oulad_abdoun,
            discoveryYear = 1925,
            latitude = 32.9,
            longitude = -6.9
        ),

        DinoLocation(
            id = 26,
            title = "Tendaguru Formation",
            imageRes = R.drawable.ic_tendaguru_formation,
            description = R.string.desc_tendaguru,
            discoveryYear = 1909,
            latitude = -10.4,
            longitude = 38.5
        ),

        DinoLocation(
            id = 27,
            title = "Bahariya Oasis",
            imageRes = R.drawable.ic_bahariya_oasis,
            description = R.string.desc_bahariya,
            discoveryYear = 1912,
            latitude = 28.3,
            longitude = 28.9
        ),

        DinoLocation(
            id = 28,
            title = "Great Artesian Basin",
            imageRes = R.drawable.ic_great_artesian_basin,
            description = R.string.desc_great_artesian_basin,
            discoveryYear = 1930,
            latitude = -27.0,
            longitude = 143.0
        ),

        DinoLocation(
            id = 29,
            title = "Waipara / Taranaki",
            imageRes = R.drawable.ic_waipara_taranaki,
            description = R.string.desc_waipara_taranaki,
            discoveryYear = 1870,
            latitude = -43.0,
            longitude = 172.7
        ),

        DinoLocation(
            id = 30,
            title = "Mount Kirkpatrick",
            imageRes = R.drawable.ic_mount_kirkpatrick,
            description = R.string.desc_mount_kirkpatrick,
            discoveryYear = 1991,
            latitude = -84.5,
            longitude = 166.3
        )
    )

    fun getLocationById(locationId: Int): DinoLocation? {
       val location = getAllLocations().find { it.id == locationId }
        return location
    }
}