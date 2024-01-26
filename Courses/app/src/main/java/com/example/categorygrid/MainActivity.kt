package com.example.categorygrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.categorygrid.ui.theme.CategoryGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CategoryGridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoursesApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun CoursesApp(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(4.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(0.5f)
        ) {
            items(DataSource.topics.subList(0, DataSource.topics.size/2)) { topic ->
                CourseCard(
                    courseImage = topic.courseImage,
                    courseName = topic.courseName,
                    courseNumber = topic.courseNumber,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
        }
        LazyColumn(
            modifier = Modifier.weight(0.5f)
        ) {
            items(DataSource.topics.subList(DataSource.topics.size/2, DataSource.topics.size)) { topic ->
                CourseCard(
                    courseImage = topic.courseImage,
                    courseName = topic.courseName,
                    courseNumber = topic.courseNumber,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun CourseCard(
    courseImage: Int,
    courseName: Int,
    courseNumber: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
        ) {
            Image(
                painter = painterResource(courseImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(68.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(courseName),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = courseNumber.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CategoryGridTheme {
        CoursesApp(modifier = Modifier.fillMaxSize())
    }
}