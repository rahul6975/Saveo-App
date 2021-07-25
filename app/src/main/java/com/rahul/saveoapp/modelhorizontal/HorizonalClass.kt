package com.rahul.saveoapp.modelhorizontal

import java.io.Serializable

data class HorizonalClass(
	val summary: String? = null,
	val image: ImageClasss? = null,
	val averageRuntime: Int? = null,
	val links: LinksClass? = null,
	val premiered: String? = null,
	val rating: RatingClass? = null,
	val runtime: Int? = null,
	val weight: Int? = null,
	val language: String? = null,
	val type: String? = null,
	val url: String? = null,
	val officialSite: String? = null,
	val network: NetworkClass? = null,
	val schedule: ScheduleClass? = null,
	val webChannel: WebChannelClass? = null,
	val genres: List<String?>? = null,
	val name: String? = null,
	val id: Int? = null,
	val externals: ExternalsClass? = null,
	val updated: Int? = null,
	val status: String? = null
)