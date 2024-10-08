package com.example.restaurant_reviewer.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restaurant_reviewer.data.remote.response.RestaurantsItem
import com.example.restaurant_reviewer.ui.common.UiState
import com.example.restaurant_reviewer.ui.components.EmptyDataItem
import com.example.restaurant_reviewer.ui.components.RestaurantItem
import com.example.restaurant_reviewer.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val query by viewModel.query

    viewModel.listRestaurant.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getSearch(query)
            }

            is UiState.Success -> {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SearchBar(
                        query = query,
                        onQueryChanges = viewModel::getSearch,
                        modifier = Modifier.background(color = MaterialTheme.colors.primary)
                    )
                    if (uiState.data.isEmpty()) EmptyDataItem(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp))
                    else {
                        HomeContent(
                            listResto = uiState.data,
                            navigateToDetail = navigateToDetail
                        )
                    }

                }
            }

            is UiState.Error -> {}
        }
    }
}
@Composable
fun HomeContent(
    listResto: List<RestaurantsItem>,
    navigateToDetail: (String) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listResto, key =  { it.id }) { data ->
            RestaurantItem(
                data.pictureId,
                data.name,
                data.city,
                data.rating,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}
