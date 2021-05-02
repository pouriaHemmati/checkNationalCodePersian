package com.national.pouria

import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val STARTUP_DELAY = 300
    val ANIM_ITEM_DURATION = 5000
    val EDITTEXT_DELAY = 300
    val BUTTON_DELAY = 500
    val VIEW_DELAY = 400


    val decelerateInterpolator = DecelerateInterpolator()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


                img_logo?.let { ViewCompat.animate(it) }
                ?.scaleY(1f)?.scaleX(1f)
                ?.setStartDelay(((BUTTON_DELAY) + 500).toLong())
                ?.setDuration(500)
                ?.setInterpolator(decelerateInterpolator)
                ?.start()


        //Here we are setting animation on displaying content
        for (i in 1 until container.childCount) {
            val v = container.getChildAt(i)
            var viewAnimator: ViewPropertyAnimatorCompat
                 viewAnimator = if (v is EditText) {
                ViewCompat.animate(v)
                        .scaleY(1f).scaleX(1f)
                        .setStartDelay((EDITTEXT_DELAY * i + 500).toLong())
                        .setDuration(500)
            } else if (v is Button) {
                ViewCompat.animate(v)
                        .scaleY(1f).scaleX(1f)
                        .setStartDelay((BUTTON_DELAY * i + 500).toLong())
                        .setDuration(500)
            } else {
                ViewCompat.animate(v)
                        .translationY(50f).alpha(1f)
                        .setStartDelay((VIEW_DELAY * i + 500).toLong())
                        .setDuration(1000)
            }
            viewAnimator.setInterpolator(DecelerateInterpolator()).start()
        }

    }


}