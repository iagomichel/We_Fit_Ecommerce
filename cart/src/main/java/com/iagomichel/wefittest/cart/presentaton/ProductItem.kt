package com.iagomichel.wefittest.cart.presentaton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.iagomichel.wefittest.base.ColorBackgroundWhite
import com.iagomichel.wefittest.base.ColorButton
import com.iagomichel.wefittest.base.ColorTextTotalCart
import com.iagomichel.wefittest.cart.R
import com.iagomichel.wefittest.common.domain.entity.MovieModel

@Composable
fun ProductItem(
    movieCart: MovieModel,
    onClickFinishCart: (idProduct: Int) -> Unit = {},
    onIncrease: (idProduct: Int) -> Unit = {},
    onDecrease: (idProduct: Int) -> Unit = {},
    onDelete: (idProduct: Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                ColorBackgroundWhite,
                RoundedCornerShape(4.dp)
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            AsyncImage(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = movieCart.image,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = movieCart.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    fontSize = 12.sp,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.LightGray
                            )
                        ) {
                            append(stringResource(R.string.cart_add_date))
                        }
                        withStyle(
                            style = SpanStyle(
                                color = ColorTextTotalCart,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(movieCart.date)
                        }
                    }
                )
            }

            IconButton(onClick = { onDelete(movieCart.id) }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_delete_item
                    ),
                    contentDescription = "",
                    tint = ColorButton
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    if (movieCart.count == 1) {
                        onDelete(movieCart.id)
                    } else
                        onDecrease(movieCart.id)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove_item),
                        contentDescription = "",
                        tint = ColorButton
                    )
                }

                Text(
                    text = movieCart.count.toString(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .width(59.dp)
                        .height(26.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(vertical = 4.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )

                IconButton(onClick = { onIncrease(movieCart.id) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_item),
                        contentDescription = "",
                        tint = ColorButton
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = stringResource(R.string.cart_subtotal),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = ColorTextTotalCart,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "R$ %.2f".format(movieCart.price),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = ColorTextTotalCart,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.cart_total),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorTextTotalCart
                )
            )

            val sumTotal = movieCart.price * movieCart.count
            Text(
                text = "R$ %.2f".format(sumTotal),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onClickFinishCart(movieCart.id) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorButton
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = stringResource(R.string.cart_finish_order),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ProductItem(
        movieCart = MovieModel(
            id = 1,
            title = "Marvel",
            price = 22.11,
            image = "",
            count = 2,
            date = "28/04/2025"
        )
    )

}