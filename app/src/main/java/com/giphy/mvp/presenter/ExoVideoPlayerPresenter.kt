package com.giphy.mvp.presenter

import com.giphy.mvp.db.VoteEntity
import com.giphy.mvp.db.VoteEntity_
import com.giphy.mvp.view.ExoVideoPlayerView
import io.objectbox.Box
import io.objectbox.BoxStore

class ExoVideoPlayerPresenter(private val boxStore: BoxStore) {

    private var exoVideoPlayerView: ExoVideoPlayerView? = null

    private lateinit var voteBox: Box<VoteEntity>
    private lateinit var voteEntity: VoteEntity

    fun attachView(view: ExoVideoPlayerView) {
        exoVideoPlayerView = view
    }

    fun getVoteCount(id: String) {
        voteBox = boxStore.boxFor(VoteEntity::class.java)
        voteBox.query().equal(VoteEntity_.videoId, id).build().findFirst().let {
            if (it == null) { //If there is no data then create new Entity
                voteEntity = VoteEntity(videoId = id)
            } else { //Already in database then just get and store in local variable
                voteEntity = it
                exoVideoPlayerView?.onUpdateVoteCount(voteEntity.totalUpVoteCount, voteEntity.totalDownVoteCount)
            }
        }
    }

    fun putUpVote() {
        //Update vote entity
        voteEntity.totalUpVoteCount++
        voteBox.put(voteEntity)
        exoVideoPlayerView?.onUpdateVoteCount(voteEntity.totalUpVoteCount, voteEntity.totalDownVoteCount)
    }

    fun putDownVote() {
        //Down vote entity
        voteEntity.totalDownVoteCount++
        voteBox.put(voteEntity)
        exoVideoPlayerView?.onUpdateVoteCount(voteEntity.totalUpVoteCount, voteEntity.totalDownVoteCount)
    }

}