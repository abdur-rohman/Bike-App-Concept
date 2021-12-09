package com.fizhu.bikeappconcept.utils.ext

import android.animation.ObjectAnimator
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.reactivex.disposables.CompositeDisposable

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.dpf: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

fun View.setMargin(left: Int, top: Int, right: Int, bottom: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(left, top, right, bottom)
    this.layoutParams = menuLayoutParams
}

fun BottomSheetBehavior<*>.expand(composite: CompositeDisposable) {
    composite.delay(500) {
        state = BottomSheetBehavior.STATE_EXPANDED
    }
}

fun BottomSheetBehavior<*>.collapse(composite: CompositeDisposable) {
    composite.delay(500) {
        state = BottomSheetBehavior.STATE_COLLAPSED
    }
}

fun BottomSheetBehavior<*>.hide() {
    if (!isHidden()) {
        state = BottomSheetBehavior.STATE_HIDDEN
    }
}

fun BottomSheetBehavior<*>.isCollapse(): Boolean {
    return state == BottomSheetBehavior.STATE_COLLAPSED
}

fun BottomSheetBehavior<*>.isExpand(): Boolean {
    return state == BottomSheetBehavior.STATE_EXPANDED
}

fun BottomSheetBehavior<*>.isHidden(): Boolean {
    return state == BottomSheetBehavior.STATE_HIDDEN
}

fun View.animTo(xy: String, size: Float) {
    ObjectAnimator.ofFloat(this, "translation$xy", size).apply {
        duration = 100
        start()
    }
}