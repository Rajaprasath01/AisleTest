package com.example.aisletest.model

data class Invites(
    val pending_invitations_count: Int,
    val profiles: List<Profile>,
    val totalPages: Int
)