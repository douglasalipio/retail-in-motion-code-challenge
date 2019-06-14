package com.douglasalipio.luasforecasts.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun computation(): Scheduler = Schedulers.computation()

fun io(): Scheduler = Schedulers.io()

fun ui(): Scheduler = AndroidSchedulers.mainThread()

// Making all {@link Scheduler}s execute
// synchronously so we can easily run assertions in our tests.
fun synComputation(): Scheduler = Schedulers.trampoline()

fun syncIo(): Scheduler = Schedulers.trampoline()

fun syncUi(): Scheduler = Schedulers.trampoline()
