package com.giphy.mvp.providers

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}