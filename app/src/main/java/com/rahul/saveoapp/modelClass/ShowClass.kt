package com.rahul.saveoapp.modelClass

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Generated("com.robohorse.robopojogenerator")
data class ShowClass(

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("image")
	val image: ImageClass? = null,

	@field:SerializedName("averageRuntime")
	val averageRuntime: Int? = null,

	@field:SerializedName("_links")
	val links: LinksClass? = null,

	@field:SerializedName("premiered")
	val premiered: String? = null,

	@field:SerializedName("rating")
	val rating: RatingClass? = null,

	@field:SerializedName("runtime")
	val runtime: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("officialSite")
	val officialSite: String? = null,

	@field:SerializedName("network")
	val network: NetworkClass? = null,

	@field:SerializedName("schedule")
	val schedule: ScheduleClass? = null,

	@field:SerializedName("genres")
	val genres: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("externals")
	val externals: ExternalsClass? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)