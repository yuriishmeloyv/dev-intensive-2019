package ru.skillbranch.devintensive.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.validUrl
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel: ViewModel() {
    private val repository: PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()
    private val repositoryError = MutableLiveData<Boolean>()
    private val isRepoError = MutableLiveData<Boolean>()

    init {
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    fun getIsRepoError():LiveData<Boolean> = isRepoError

    fun getProfileData(): LiveData<Profile>{
        return profileData
    }

    fun getTheme(): LiveData<Int> = appTheme

    fun getRepositoryError(): LiveData<Boolean> = repositoryError

    fun saveProfileData(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES)
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        else appTheme.value = AppCompatDelegate.MODE_NIGHT_YES

        repository.saveAppTheme(appTheme.value!!)
    }

    fun onRepositoryChanged(repository: String) {
        //repositoryError.value = isValidateRepository(repository)
        repositoryError.value = !repository.validUrl()
    }


    fun onRepoEditCompleted(isError: Boolean) {
        isRepoError.value = isError
    }

    private fun isValidateRepository(repoText: String): Boolean {
        val regexStr = "^(?:https://)?(?:www.)?(?:github.com/)[^/|\\s]+(?<!${getRegexExceptions()})(?:/)?$"
        val regex = Regex(regexStr)

        return (repoText.isNotEmpty() && !regex.matches(repoText))
    }

    private fun getRegexExceptions(): String {
        val exceptions = arrayOf(
            "enterprise", "features", "topics", "collections", "trending", "events", "marketplace", "pricing",
            "nonprofit", "customer-stories", "security", "login", "join"
        )
        return exceptions.joinToString("|\\b","\\b")
    }
}