package com.lihan.thecatsamplecode.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.lihan.thecatsamplecode.domain.model.Cat

@Composable
fun CatItem(
    modifier: Modifier = Modifier,
    cat: Cat,
    onClick: (Cat) -> Unit = {}
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        onClick = {
            onClick(cat)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                model = cat.url,
                contentDescription = "Server Image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit,
                success = { state ->
                    val aspectRatio = state.painter.intrinsicSize.let { size ->
                        if (size.width > 0 && size.height > 0) size.width / size.height else 1f
                    }
                    Image(
                        painter = state.painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(aspectRatio),
                        contentScale = ContentScale.Fit
                    )
                }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ,
                text = "Width ${cat.width} / Height ${cat.height}",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}