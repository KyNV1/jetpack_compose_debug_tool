package com.example.jetpack_compose_debug_tool.data


val dummySocialData = listOf(
    SocialLinkInfo(
        id = 1,
        label = "Twitter",
        url = "https://www.twitter.com",
        value = "3"
    ),
    SocialLinkInfo(
        id = 2,
        label = "GitHub",
        url = "https://www.github.com",
        value = "10"
    ),
    SocialLinkInfo(
        id = 3,
        label = "LinkedIn",
        url = "https://www.linkedin.com",
        value = "5"
    ),
    SocialLinkInfo(
        id = 4,
        label = "Facebook",
        url = "https://www.facebook.com",
        value = "25"
    )
)


data class SocialLinkInfo(
    val id: Int,
    val label: String,
    val url: String,
    val value: String // Giá trị trong ô text field
)