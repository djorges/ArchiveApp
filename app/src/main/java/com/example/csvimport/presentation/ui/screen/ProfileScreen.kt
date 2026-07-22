package com.example.csvimport.presentation.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csvimport.R


private val PrimaryBlue = Color(0xFFF44336)
private val LightBlue = Color(0xFFFFEBEE)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onMenu: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Options",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back_24),
                            null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onMenu) {
                        Icon(painter = painterResource(R.drawable.more_horiz_24), null)
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(Modifier.height(8.dp))

            ProfileHeader()

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Edit Profile")
            }

            Spacer(Modifier.height(24.dp))

            ProfileItem(
                icon = R.drawable.outline_notifications_24,
                title = "Notifications"
            )

            ProfileItem(
                icon = R.drawable.settings_24,
                title = "Advanced Settings"
            )

            ProfileItem(
                icon = R.drawable.docs_24,
                title = "Terms of Service"
            )

            ProfileItem(
                icon = R.drawable.language_24,
                title = "Language",
                value = "English"
            )

            ProfileItem(
                icon = R.drawable.logout_24,
                title = "Log out",
                showArrow = false
            )

            Spacer(Modifier.weight(1f))

            HelpCard()

            Spacer(Modifier.height(20.dp))

            //BottomNavigationBar()
        }
    }
}

@Composable
private fun ProfileHeader(
    name: String = "John Carter",
    email: String = "john@profile.com",
    imageUrl: String = ""
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        //TODO: Add Coil later
        /*
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
       */
        Surface(
            modifier = Modifier.size(64.dp),
            shape = CircleShape,
            color = Color.LightGray
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(R.drawable.person_24),
                    null,
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        Spacer(Modifier.width(16.dp))

        Column {

            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = email,
                color = Color.Gray
            )
        }
    }
}


@Composable
private fun ProfileItem(
    @DrawableRes icon: Int,
    title: String,
    value: String? = null,
    showArrow: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(LightBlue),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = PrimaryBlue,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(Modifier.width(16.dp))

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 17.sp
        )

        value?.let {
            Text(
                text = it,
                color = Color.Gray
            )

            Spacer(Modifier.width(6.dp))
        }

        if (showArrow) {
            Icon(
                painter = painterResource(R.drawable.keyboard_arrow_right_24),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
private fun HelpCard() {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = LightBlue
    ) {

        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.support_agent_24),
                null,
                tint = PrimaryBlue
            )

            Spacer(Modifier.width(12.dp))

            Text(
                "How can we help you?",
                color = PrimaryBlue,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBar() {

    NavigationBar {

        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(painter = painterResource(R.drawable.home_24), null)
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(painter = painterResource(R.drawable.qr_code_scanner_24), null)
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(painter = painterResource(R.drawable.person_24), null)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}