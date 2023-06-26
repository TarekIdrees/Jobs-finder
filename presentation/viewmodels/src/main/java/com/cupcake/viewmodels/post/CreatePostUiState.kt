package com.cupcake.viewmodels.post

import com.cupcake.viewmodels.base.BaseErrorUiState
import java.io.File

data class CreatePostUiState(
    val post: PostUiState = PostUiState(),
    val isPostCreated: Boolean = false,
    val isLoading: Boolean = false,
    val error: BaseErrorUiState? = null,
    val postImage:  File? = null,
    val isImageSelectionCanceled: Boolean = false
) {
    data class PostUiState(
        val id: String = "",
        val content: String = "",
        val createAt: String = "",
    )
}
