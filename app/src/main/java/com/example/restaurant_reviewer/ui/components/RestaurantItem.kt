package com.example.restaurant_reviewer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.restaurant_reviewer.ui.theme.RestaurantAppTheme
import com.example.restaurant_reviewer.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RestaurantItem(
    pictureId: String,
    name: String,
    city: String,
    rating: Double,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.width(360.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            GlideImage(
                model = "https://restaurant-api.dicoding.dev/images/medium/$pictureId",
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp)) {
                Text(
                   text = name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Text(
                    text = city,
                    overflow = TextOverflow.Ellipsis,
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star_24),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryVariant
                    )

                    Text(
                        text = rating.toString(),
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantItemPreview() {
    RestaurantAppTheme {
        RestaurantItem(
                "17",
                "Medan",
                "Mantep banget",
                4.9,
        )
    }
}