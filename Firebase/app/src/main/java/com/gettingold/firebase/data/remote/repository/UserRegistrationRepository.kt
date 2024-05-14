package com.gettingold.firebase.data.remote.repository

import com.gettingold.firebase.data.remote.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.getValue
import com.google.firebase.database.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

interface UserRegistrationAccessor {
    fun getAllUsers(): Flow<List<User>>
    fun getUser(id: String): Flow<User>
    fun addUser(user: User): Unit
    fun editUser(user: User): Unit
    fun deleteAllUsers(): Unit
    fun deleteUser(id: String): Unit
}

class UserRegistrationRepository @Inject constructor(val reference: DatabaseReference):
    UserRegistrationAccessor {

    override fun getAllUsers(): Flow<List<User>> {
        return reference.child("users").snapshots.map { snapshot ->
            snapshot.children.mapNotNull {
                it.getValue<User>()
            }
        }
    }

    override fun getUser(id: String): Flow<User> {
        return reference.child("users").child(id).snapshots.mapNotNull {
            it.getValue<User>()
        }
    }

    override fun addUser(user: User) {
        val newNode = reference.child("users").push()
        newNode.setValue(user.copy(id = newNode.key!!))
    }

    override fun editUser(user: User) {
        val updates = hashMapOf<String, Any>(
            "/users/${user.id}" to user
        )

        reference.updateChildren(updates)
    }

    override fun deleteAllUsers() {
        reference.child("users").removeValue()
    }

    override fun deleteUser(id: String) {
        reference.child("users").child(id).removeValue()
    }
}