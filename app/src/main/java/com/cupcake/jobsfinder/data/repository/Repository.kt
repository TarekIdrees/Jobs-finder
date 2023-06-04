package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import kotlinx.coroutines.flow.Flow

interface Repository {

  suspend fun getAllPosts(): Flow<List<PostDto>>

  suspend fun getAllJobTitles():List<JobTitleDto>

  suspend fun createJob(jobInfo: JobDto): Boolean

  suspend fun getAllJobs(): Flow<List<JobDto>>

    // region Job

    suspend fun getJobById(jobId: Int): JobDto

    //endregion


    // region Post
    suspend fun createPost(content: String): PostDto



    suspend fun getPostById(id: String): PostDto


    //endregion

}