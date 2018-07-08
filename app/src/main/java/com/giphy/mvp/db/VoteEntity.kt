package com.giphy.mvp.db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index

@Entity
data class VoteEntity(
        @Id var id: Long = 0,
        @Index var videoId: String,
        @JvmField var totalUpVoteCount: Long = 0,
        @JvmField var totalDownVoteCount: Long = 0
)
