package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.models.User
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.SingleLiveEvent
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.ext.route

class RegisterViewModel(
    private val repository: Repository
) : BaseViewModel() {

    val photo: MutableLiveData<String> = MutableLiveData()
    val fullName: MutableLiveData<String> = MutableLiveData()
    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val repeatPassword: MutableLiveData<String> = MutableLiveData()
    private val _isUsernameExist = SingleLiveEvent<Boolean>()
    val isUsernameExist: LiveData<Boolean>
        get() = _isUsernameExist

    private val _isRegistered = SingleLiveEvent<Boolean>()
    val isRegistered: LiveData<Boolean>
        get() = _isRegistered

    fun checkUsername() {
        compositeDisposable.route(repository.getUserByUsername(username.value ?: ""),
            io = {
                if (it.isNotEmpty()) {
                    _isUsernameExist.postValue(true)
                } else {
                    _isUsernameExist.postValue(false)
                }
            },
            error = {
                _isUsernameExist.postValue(true)
            }
        )
    }

    fun submitRegister() {
        repository.insertUser(
            User(
                id = null,
                name = fullName.value,
                username = username.value,
                password = password.value,
                photo = photo.value?:""
            )
        )
        _isRegistered.postValue(true)
    }

}