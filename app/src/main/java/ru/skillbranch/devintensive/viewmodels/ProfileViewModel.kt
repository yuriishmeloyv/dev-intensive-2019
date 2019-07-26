package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel: ViewModel(){

    private val repository : PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()
    private val repositoryError = MutableLiveData<Boolean>()
    private val isRepositoryError = MutableLiveData<Boolean>()

    init {
        Log.d("M_ProfileViewModel", "Init view model")
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("M_ProfileViewModel","View model cleared")
    }

    fun getProfileData() : LiveData<Profile> = profileData

    fun getAppTheme(): MutableLiveData<Int> = appTheme

    fun getIsRepositoryError():LiveData<Boolean> = isRepositoryError

    fun getRepositoryError(): LiveData<Boolean> = repositoryError

    fun saveProfileData(profile: Profile){
        repository.saveProfile(profile)
        profileData.value = profile

    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES){
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        }else{
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES

        }

        repository.saveAppTheme(appTheme.value!!)
    }

    fun onRepositoryChanged(repository: String) {
        repositoryError.value = isRepositoryValidate(repository)
    }


    fun onRepositoryEditCompleted(isError: Boolean) {
        isRepositoryError.value = isError
    }

    private fun isRepositoryValidate(repositoryText: String): Boolean {
        val regexStr = "^(?:https://)?(?:www.)?(?:github.com/)[^/|\\s]+(?<!${getRepositoryRegexExceptions()})(?:/)?$"
        val regex = Regex(regexStr)

        return (repositoryText.isNotEmpty() && !regex.matches(repositoryText))
    }

    private fun getRepositoryRegexExceptions(): String {
        val exceptions = arrayOf(
            "enterprise", "features", "topics", "collections", "trending", "events", "marketplace", "pricing",
            "nonprofit", "customer-stories", "security", "login", "join"
        )
        return exceptions.joinToString("|\\b","\\b")
    }

}