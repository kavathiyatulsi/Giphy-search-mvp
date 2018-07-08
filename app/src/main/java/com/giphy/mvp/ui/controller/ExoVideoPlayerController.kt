package com.giphy.mvp.ui.controller

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devbrackets.android.exomedia.listener.OnPreparedListener
import com.giphy.mvp.R
import com.giphy.mvp.db.ObjectBox
import com.giphy.mvp.presenter.ExoVideoPlayerPresenter
import com.giphy.mvp.view.ExoVideoPlayerView
import com.google.android.exoplayer2.Player
import kotlinx.android.synthetic.main.exo_video_player_controller.view.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ExoVideoPlayerController(args: Bundle?) : BaseController(args), OnPreparedListener, View.OnClickListener, ExoVideoPlayerView {

    private lateinit var controllerView: View
    private lateinit var exoVideoPlayerPresenter: ExoVideoPlayerPresenter

    companion object {
        private const val keyVideoID = "videoId"
        private const val keyVideoURL = "keyVideoURL"
        fun getInstance(id: String, url: String): ExoVideoPlayerController {
            val args = Bundle()
            args.putString(keyVideoID, id)
            args.putString(keyVideoURL, url)
            return ExoVideoPlayerController(args)
        }
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.exo_video_player_controller, container, false)
    }

    override fun onActivityInject() {
        exoVideoPlayerPresenter = ExoVideoPlayerPresenter(ObjectBox.boxStore)
        exoVideoPlayerPresenter.attachView(this@ExoVideoPlayerController)
    }

    override fun onViewBound(view: View) {
        controllerView = view

        val videoUrl = args.getString(keyVideoURL)

        //Up and Down vote click Listener
        view.rlvUpVote.setOnClickListener(this@ExoVideoPlayerController)
        view.rlvDownVote.setOnClickListener(this@ExoVideoPlayerController)

        //Initialize Video PlayerView
        inItVideoPlayerView(videoUrl)

        //Get Vote Entity from ObjectBox
        exoVideoPlayerPresenter.getVoteCount(args.getString(keyVideoID))

    }

    private fun inItVideoPlayerView(videoUrl: String) {
        controllerView.videoPlayerView?.setOnPreparedListener(this@ExoVideoPlayerController)
        controllerView.videoPlayerView?.setVideoURI(Uri.parse(videoUrl))
        controllerView.videoPlayerView?.setRepeatMode(Player.REPEAT_MODE_ALL)
    }

    override fun onPrepared() {
        controllerView.videoPlayerView?.start()
    }

    @SuppressLint("SetTextI18n")
    override fun onUpdateVoteCount(totalUpVote: Long, totalDownVote: Long) {
        controllerView.tvUpVoteCount.text = "($totalUpVote)"
        controllerView.tvDownVoteCount.text = "($totalDownVote)"
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.rlvUpVote -> {
                exoVideoPlayerPresenter.putUpVote()
            }
            R.id.rlvDownVote -> {
                exoVideoPlayerPresenter.putDownVote()
            }
        }
    }
}