package com.example.deliverynationclone.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.deliverynationclone.R
import kotlin.reflect.KClass

//link https://sungjk.github.io/2019/09/07/kotlin-reified.html
//일반적인 제네릭 함수 body에서 타입 T는 컴파일 타임에는 존재하지만 런타임에는 Type erasure 때문에 접근할 수 없다.
// 따라서 일반적인 클래스에 작성된 함수 body에서 제네릭 타입에 접근하고 싶다면
// genericFunc 처럼 명시적으로 타입을 파라미터로 전달해주어야 한다.
//하지만 reified type parameter 와 함께 inline 함수를 만들면
//추가적으로 Class<T>를 파라미터로 넘겨줄 필요 없이 런타임에 타입 T에 접근할 수 있다.
// 따라서 myVar is T 처럼 변수가 T의 인스턴스인지 쉽게 검사할 수 있다.

inline fun <reified T : Fragment> FragmentManager.replace() {
    commit {
        replace<T>(R.id.fc_main)
        setReorderingAllowed(true)
    }
}

inline fun <reified T : Fragment> FragmentManager.replaceSlide(backStackName: String) {
    commit {
        setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
        replace<T>(R.id.fc_main)
        setReorderingAllowed(true)
        addToBackStack(backStackName)
    }
}
