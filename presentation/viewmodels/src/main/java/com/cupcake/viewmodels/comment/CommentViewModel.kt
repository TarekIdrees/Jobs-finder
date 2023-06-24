package com.cupcake.viewmodels.comment

import com.cupcake.models.Comment
import com.cupcake.models.Post
import com.cupcake.usecase.GetAllCommentsUseCase
import com.cupcake.usecase.GetPostByIdUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getPostById: GetPostByIdUseCase,
    private val commentsUseCase: GetAllCommentsUseCase
) : BaseViewModel<CommentUiState>(CommentUiState()), CommentInteractionListener {



    fun getPost(id: String) {
        updateState { it.copy(isLoading = true, isSuccess = false, error = null) }
        tryToExecute(
            callee = { getPostById(id) },
            onSuccess = ::onSuccessGetPost,
            onError = ::onErrorGetPost
        )
    }

    private fun onSuccessGetPost(post: Post) {
        updateState { it.copy(isLoading = false, post = post.toUiPost(), isSuccess = true, error = null) }
    }

    private fun onErrorGetPost(error: BaseErrorUiState) {
        updateState { it.copy(isLoading = false, error = error, isSuccess = false) }
    }

     fun getCommentsPost(id: String) {
        tryToExecute(
            { commentsUseCase(id) },
            ::onGetCommentsPostSuccess,
            ::onGetCommentPostFailure
        )
    }

    private fun onGetCommentsPostSuccess(comment: List<Comment>) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                comments = comment.map { comment -> comment.toCommentUiState() })
        }
    }

    private fun onGetCommentPostFailure(error: BaseErrorUiState) {
        _state.update {
            it.copy(isLoading = false, error = error)
        }
    }

    private fun Post.toUiPost(): CommentUiState.PostUiState {
        return CommentUiState.PostUiState(
            id = id,
            content = content,
            createdAt = createdAt,
            creatorName = creatorName,
            profileImage = profileImage,
            jobTitle = jobTitle
            )
    }


    private fun Comment.toCommentUiState(): CommentUiState.CommentUiState{
        return CommentUiState.CommentUiState(
            id = id,
            postId = postId,
            totalLikes = totalLikes,
            content = content,
            createAt = createAt,
            commentAuthor = commentAuthor,
            jobTitle = jobTitle,
            profileImage = profileImage
        )
    }

    override fun onLikeClick(id: String) {
//        _state.update { currentState ->
//            val updateComments=currentState.comments.map { comment ->
//                if (comment.id==id){
//                    post.copy(isLiked = !post.isLiked, likes = if (post.isLiked) post.likes-1 else post.likes+1)
//                }else{
//                    post
//                }
//            }
//            currentState.copy(posts = updateComments)
//        }
   }

}