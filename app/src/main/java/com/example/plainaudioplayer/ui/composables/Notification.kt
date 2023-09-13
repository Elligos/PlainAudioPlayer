package com.example.plainaudioplayer.ui.composables


import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plainaudioplayer.R
import com.example.plainaudioplayer.ui.theme.PlainAudioPlayerTheme
//import com.example.plainaudioplayer.ui.theme.myStyle
//import com.example.plainaudioplayer.ui.theme.newColor

// Отображение элементов в Compose настраивается с помощью аргументов составной функции и Modifier.
// Аргументы используются только для специфичных параметров элемента, а
// TODO("4.1 Модификаторы для параметров, можно применить к любому элементу Compose.")

// TODO("4.6 Модификаторы можно без проблем переиспользовать.")
// Для этого достаточно просто объявить свойство с цепочкой переиспользуемых модификаторов.
val maxWidthGrayModifier = Modifier
    // TODO("4.5 Модификаторы, переданные в аргументы можно дополнять и объединять в цепочки. Порядок модификаторов важен!")
    // Каждый Modifier при вызове вносит изменения в результат предыдущего модификатора.
    .background(Color.LightGray)
    .fillMaxWidth()
    .padding(16.dp)

@Composable
fun Notification(
    // При этом обратите внимание, что
    // TODO("4.2 Порядок аргументов для Composable является хорошей практикой.")
    // Сначала идут обязательные параметры без значений по-умолчанию,
    clickNotification: () -> Unit,
    // затем modifier. (Хорошей практикой является объявление аргумента по-умолчанию.)
    modifier: Modifier = Modifier,
    // TODO("4.4 Для дочерних элементов так же можно объявлять аргумент modifier.")
    // Это сделает ваш код более переиспользуемым, а поведение более предсказуемым и понятным.
    // Имена для параметров нужно выбирать таким образом, чтобы родительский элемент назывался modifier,
    // а все остальные отображали в названии элемент к которому этот модификатор будет применяться.
    messageModifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    // и после него все аргументы со значением по-умолчанию.
//    textNotification: String = stringResource(R.string.ui_notification_main_text),
    textNotification: String = "Notification"
) {
    Column(
        modifier = modifier
    ) {
        // this: ColumnScope ->
        // Compose поддерживает безопасность типов для Modifier, это означает, что модификаторы,
        // которые созданы для одного составного объекта и могут применяться к дочерним элементам
        // этого объекта не могут применяться к дочерним элементам другого составного объекта.
        // Достигается это за счет объявления скоупа в котором определены модификаторы.

//        Greeting(
//            name = stringResource(id = R.string.app_name),
//            count = integerResource(id = R.integer.ui_message_count)
//        )

        Row(
            modifier = maxWidthGrayModifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                // Например, для Text есть такие специфические параметры, как цвет, размер и стиль шрифта и другие параметры для отображения текста
                text = textNotification,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
                // Но если вам нужно будет, например, задать паддинги или настроить background, то сделать это можно уже при помощи Modifier.
                modifier = messageModifier
            )
            Button(
                onClick = clickNotification,
                // TODO("4.3 Модификаторы можно применить к любому составному объекту, передав его в качестве аргумента функции.")
                modifier = buttonModifier
            ) {
//                Text(text = stringResource(R.string.button_text_ok))
                Text(text = "Ok")
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Composable
fun NotificationPreview() {
    PlainAudioPlayerTheme {
        Notification(
            textNotification = "Notification",
            clickNotification = {
                Log.i("TAG#", "NotificationPreview: clickNotification")
            }
        )
    }
}