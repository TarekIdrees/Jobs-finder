package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.JobApiService
import javax.inject.Inject
import com.cupcake.jobsfinder.data.remote.response.PostDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import kotlinx.coroutines.delay


class RepositoryImpl @Inject constructor(
    private val api: JobApiService
) : Repository {
  
    override suspend fun getAllJobs(): Flow<List<JobDto>> {
      return flow {
          val response = api.getAllJobs()
          if (response.isSuccessful) {
              response.body()?.value?.let { emit(it) }
          } else {
              throw Exception(response.message())
          }
      }.flowOn(Dispatchers.IO)
    }
      
    override suspend fun getAllPosts(): Flow<List<PostDto>> {
        return fakePosts() //todo:[ call getAllPosts from JopApiService]
    }

    private fun fakePosts(): Flow<List<PostDto>> {
        return flow {
            emit(
                listOf(
                    PostDto("1", 9992453L, "android developer",),
                    PostDto("1", 9992453L, "android developer",),
                    PostDto("1", 9992453L, "android developer",)
                )
            )
        }.flowOn(Dispatchers.IO)
    }
    
    override suspend fun getAllJobTitles(): List<JobTitleDto> {
        return listOf(
            JobTitleDto(
                id = "ID HERE",
                title = "Android"
            )
        )
    }
    
    override suspend fun createJob(jobInfo: JobDto): Boolean {
        return try {
            val job = api.createJob(jobInfo)
            // need more logic
            return true
        } catch (e: Throwable) {
            return false
        }
    }
   	override suspend fun createPost(content: String): Boolean {
		delay(2000)
		return true
	  }


}