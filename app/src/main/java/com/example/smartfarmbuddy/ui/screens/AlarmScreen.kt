package com.example.smartfarmbuddy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartfarmbuddy.data.CareType
import com.example.smartfarmbuddy.data.Plant
import com.example.smartfarmbuddy.data.PlantCareManager
import com.example.smartfarmbuddy.data.PlantDataSource
import com.example.smartfarmbuddy.data.findPlantByName
import com.example.smartfarmbuddy.data.model.City
import com.example.smartfarmbuddy.ui.viewmodel.WeatherViewModel

private val plantList = listOf(
    "酒瓶兰", "橡皮树", "南洋杉", "发财树", "铁树", "荷兰铁", "巴西木",
    "月季", "杜鹃", "牡丹", "茉莉",
    "绿萝", "吊兰", "芦荟", "仙人掌",
    "玫瑰", "菊花", "兰花", "荷花",
    "常春藤", "紫藤", "葡萄"
)

private val cityData = mapOf(
    "宁夏" to listOf(
        City("101170101", "银川", "中国", "宁夏", "38.4778", "106.2344", "https://amap.com"),
        City("101170201", "石嘴山", "中国", "宁夏", "39.0438", "106.3901", "https://amap.com"),
        City("101170301", "吴忠", "中国", "宁夏", "37.9972", "106.2139", "https://amap.com"),
        City("101170401", "固原", "中国", "宁夏", "36.0153", "106.2789", "https://amap.com"),
        City("101170501", "中卫", "中国", "宁夏", "37.5128", "105.1883", "https://amap.com")
    ),
    "北京" to listOf(
        City("101010100", "北京", "中国", "北京", "39.9042", "116.4074", "https://amap.com"),
        City("101010200", "朝阳", "中国", "北京", "39.9442", "116.4368", "https://amap.com"),
        City("101010300", "海淀", "中国", "北京", "39.9963", "116.2993", "https://amap.com")
    ),
    "广东" to listOf(
        City("101280101", "广州", "中国", "广东", "23.1291", "113.2644", "https://amap.com"),
        City("101280601", "深圳", "中国", "广东", "22.5431", "114.0579", "https://amap.com"),
        City("101280701", "珠海", "中国", "广东", "22.2754", "113.5679", "https://amap.com")
    ),
    "上海" to listOf(
        City("101020100", "上海", "中国", "上海", "31.2304", "121.4737", "https://amap.com"),
        City("101020200", "浦东", "中国", "上海", "31.2397", "121.525", "https://amap.com"),
        City("101020300", "黄浦", "中国", "上海", "31.2336", "121.4944", "https://amap.com")
    )
)

@Composable
fun AlarmScreen() {
    val weatherViewModel: WeatherViewModel = viewModel()
    val selectedCity = weatherViewModel.getSelectedCity()

    var waterReminder by remember { mutableStateOf(PlantCareManager.isCareEnabled(CareType.WATER)) }
    var loosenReminder by remember { mutableStateOf(PlantCareManager.isCareEnabled(CareType.LOOSEN)) }
    var fertilizeReminder by remember { mutableStateOf(PlantCareManager.isCareEnabled(CareType.FERTILIZE)) }

    var selectedPlant by remember { mutableStateOf(PlantCareManager.getSelectedPlant()) }
    var showPlantDropdown by remember { mutableStateOf(false) }

    var selectedProvince by remember { mutableStateOf(selectedCity.province) }
    var selectedCityItem by remember { mutableStateOf<City?>(selectedCity) }
    var showProvinceDropdown by remember { mutableStateOf(false) }
    var showCityDropdown by remember { mutableStateOf(false) }

    var showWaterFrequencyDialog by remember { mutableStateOf(false) }
    var showLoosenFrequencyDialog by remember { mutableStateOf(false) }
    var showFertilizeFrequencyDialog by remember { mutableStateOf(false) }

    var customWaterFrequency by remember { mutableStateOf("") }
    var customLoosenFrequency by remember { mutableStateOf("") }
    var customFertilizeFrequency by remember { mutableStateOf("") }

    val currentPlant = findPlantByName(selectedPlant)

    LaunchedEffect(selectedPlant) {
        PlantCareManager.setSelectedPlant(selectedPlant)
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFFCFBF0)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ReminderSection(
                waterChecked = waterReminder,
                loosenChecked = loosenReminder,
                fertilizeChecked = fertilizeReminder,
                onWaterChange = { waterReminder = it },
                onLoosenChange = { loosenReminder = it },
                onFertilizeChange = { fertilizeReminder = it },
                onSave = {
                    PlantCareManager.setCareEnabled(CareType.WATER, waterReminder)
                    PlantCareManager.setCareEnabled(CareType.LOOSEN, loosenReminder)
                    PlantCareManager.setCareEnabled(CareType.FERTILIZE, fertilizeReminder)
                }
            )

            PlantSelector(
                selectedPlant = selectedPlant,
                showDropdown = showPlantDropdown,
                onShowDropdownChange = { showPlantDropdown = it },
                onPlantSelect = {
                    selectedPlant = it
                    showPlantDropdown = false
                }
            )

            FrequencySettingSection(
                plant = currentPlant,
                onWaterClick = { showWaterFrequencyDialog = true },
                onLoosenClick = { showLoosenFrequencyDialog = true },
                onFertilizeClick = { showFertilizeFrequencyDialog = true }
            )

            CitySelector(
                selectedProvince = selectedProvince,
                selectedCity = selectedCityItem,
                showProvinceDropdown = showProvinceDropdown,
                showCityDropdown = showCityDropdown,
                onProvinceShowChange = { showProvinceDropdown = it },
                onCityShowChange = { showCityDropdown = it },
                onProvinceSelect = { province ->
                    selectedProvince = province
                    val cities = cityData[province] ?: emptyList()
                    selectedCityItem = cities.firstOrNull()
                    showProvinceDropdown = false

                    selectedCityItem?.let {
                        weatherViewModel.selectCity(it)
                    }
                },
                onCitySelect = { city ->
                    selectedCityItem = city
                    showCityDropdown = false
                    weatherViewModel.selectCity(city)
                }
            )
        }
    }

    if (showWaterFrequencyDialog) {
        FrequencyDialog(
            title = "浇水频率设置",
            defaultFrequency = currentPlant?.waterFrequencyDays ?: 7,
            currentValue = customWaterFrequency,
            onValueChange = { customWaterFrequency = it },
            onConfirm = {
                val days = customWaterFrequency.toIntOrNull()
                if (days != null && days > 0) {
                    PlantCareManager.setCustomFrequency(CareType.WATER, days)
                } else {
                    PlantCareManager.clearCustomFrequency(CareType.WATER)
                }
                showWaterFrequencyDialog = false
                customWaterFrequency = ""
            },
            onCancel = {
                showWaterFrequencyDialog = false
                customWaterFrequency = ""
            }
        )
    }

    if (showLoosenFrequencyDialog) {
        FrequencyDialog(
            title = "松土频率设置",
            defaultFrequency = currentPlant?.loosenFrequencyDays ?: 14,
            currentValue = customLoosenFrequency,
            onValueChange = { customLoosenFrequency = it },
            onConfirm = {
                val days = customLoosenFrequency.toIntOrNull()
                if (days != null && days > 0) {
                    PlantCareManager.setCustomFrequency(CareType.LOOSEN, days)
                } else {
                    PlantCareManager.clearCustomFrequency(CareType.LOOSEN)
                }
                showLoosenFrequencyDialog = false
                customLoosenFrequency = ""
            },
            onCancel = {
                showLoosenFrequencyDialog = false
                customLoosenFrequency = ""
            }
        )
    }

    if (showFertilizeFrequencyDialog) {
        FrequencyDialog(
            title = "施肥频率设置",
            defaultFrequency = currentPlant?.fertilizeFrequencyDays ?: 30,
            currentValue = customFertilizeFrequency,
            onValueChange = { customFertilizeFrequency = it },
            onConfirm = {
                val days = customFertilizeFrequency.toIntOrNull()
                if (days != null && days > 0) {
                    PlantCareManager.setCustomFrequency(CareType.FERTILIZE, days)
                } else {
                    PlantCareManager.clearCustomFrequency(CareType.FERTILIZE)
                }
                showFertilizeFrequencyDialog = false
                customFertilizeFrequency = ""
            },
            onCancel = {
                showFertilizeFrequencyDialog = false
                customFertilizeFrequency = ""
            }
        )
    }
}

@Composable
fun FrequencySettingSection(
    plant: Plant?,
    onWaterClick: () -> Unit,
    onLoosenClick: () -> Unit,
    onFertilizeClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "护理频率设置:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            FrequencyItem(
                label = "浇水频率",
                defaultDays = plant?.waterFrequencyDays ?: 7,
                customDays = getCustomFrequency(CareType.WATER),
                onClick = onWaterClick
            )

            FrequencyItem(
                label = "松土频率",
                defaultDays = plant?.loosenFrequencyDays ?: 14,
                customDays = getCustomFrequency(CareType.LOOSEN),
                onClick = onLoosenClick
            )

            FrequencyItem(
                label = "施肥频率",
                defaultDays = plant?.fertilizeFrequencyDays ?: 30,
                customDays = getCustomFrequency(CareType.FERTILIZE),
                onClick = onFertilizeClick
            )

            Text(
                text = "* 点击可自定义频率，留空则使用植物默认值",
                fontSize = 12.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun FrequencyItem(
    label: String,
    defaultDays: Int,
    customDays: Int?,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF333333)
        )

        val displayText = if (customDays != null) {
            "${customDays}天 (自定义)"
        } else {
            "${defaultDays}天 (默认)"
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = displayText,
                fontSize = 14.sp,
                color = if (customDays != null) Color(0xFF00CD00) else Color(0xFF666666)
            )

            androidx.compose.material3.Icon(
                imageVector = Icons.Default.ExpandMore,
                contentDescription = "设置",
                modifier = Modifier
                    .size(16.dp)
                    .padding(start = 8.dp)
            )
        }
    }
}

private fun getCustomFrequency(careType: CareType): Int? {
    return try {
        val plant = com.example.smartfarmbuddy.data.PlantDataSource.categories
            .flatMap { it.plants }
            .find { it.name == PlantCareManager.getSelectedPlant() }
        if (plant != null) {
            val defaultFreq = when (careType) {
                CareType.WATER -> plant.waterFrequencyDays
                CareType.LOOSEN -> plant.loosenFrequencyDays
                CareType.FERTILIZE -> plant.fertilizeFrequencyDays
            }
            val freq = PlantCareManager.getFrequencyDays(careType, plant)
            if (freq != defaultFreq) freq else null
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

@Composable
fun FrequencyDialog(
    title: String,
    defaultFrequency: Int,
    currentValue: String,
    onValueChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    androidx.compose.ui.window.Dialog(onDismissRequest = onCancel) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.White,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "默认频率: $defaultFrequency 天",
                    fontSize = 14.sp,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                androidx.compose.material3.OutlinedTextField(
                    value = currentValue,
                    onValueChange = onValueChange,
                    label = { androidx.compose.material3.Text("自定义频率（天）") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Surface(
                        modifier = Modifier
                            .clickable { onCancel() }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color(0xFFEEEEEE)
                    ) {
                        Text(
                            text = "取消",
                            fontSize = 14.sp,
                            color = Color(0xFF666666)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Surface(
                        modifier = Modifier
                            .clickable { onConfirm() }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color(0xFF00CD00)
                    ) {
                        Text(
                            text = "确定",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Spacer(modifier: Modifier) {
    androidx.compose.foundation.layout.Spacer(modifier = modifier)
}

@Composable
fun ReminderSection(
    waterChecked: Boolean,
    loosenChecked: Boolean,
    fertilizeChecked: Boolean,
    onWaterChange: (Boolean) -> Unit,
    onLoosenChange: (Boolean) -> Unit,
    onFertilizeChange: (Boolean) -> Unit,
    onSave: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "种植提醒设置:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Column {
                ReminderCheckBox("浇水提醒", waterChecked, onWaterChange)
                ReminderCheckBox("松土提醒", loosenChecked, onLoosenChange)
                ReminderCheckBox("施肥提醒", fertilizeChecked, onFertilizeChange)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Surface(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable { onSave() },
                    color = Color(0xFFDDDDDD)
                ) {
                    Text(
                        text = "保存提醒",
                        fontSize = 14.sp,
                        color = Color(0xFF666666),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ReminderCheckBox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF333333)
        )

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun PlantSelector(
    selectedPlant: String,
    showDropdown: Boolean,
    onShowDropdownChange: (Boolean) -> Unit,
    onPlantSelect: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "种植植物种类设置: (当前为$selectedPlant)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            DropdownSelector(
                selectedText = selectedPlant,
                showDropdown = showDropdown,
                options = plantList,
                onShowChange = onShowDropdownChange,
                onSelect = onPlantSelect
            )
        }
    }
}

@Composable
fun CitySelector(
    selectedProvince: String,
    selectedCity: City?,
    showProvinceDropdown: Boolean,
    showCityDropdown: Boolean,
    onProvinceShowChange: (Boolean) -> Unit,
    onCityShowChange: (Boolean) -> Unit,
    onProvinceSelect: (String) -> Unit,
    onCitySelect: (City) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "天气城市设置: (当前为${selectedCity?.name ?: "未选择"})",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            DropdownSelector(
                selectedText = selectedProvince,
                showDropdown = showProvinceDropdown,
                options = cityData.keys.toList(),
                onShowChange = onProvinceShowChange,
                onSelect = onProvinceSelect
            )

            DropdownSelector(
                selectedText = selectedCity?.name ?: "",
                showDropdown = showCityDropdown,
                options = cityData[selectedProvince]?.map { it.name } ?: emptyList(),
                onShowChange = onCityShowChange,
                onSelect = { cityName ->
                    val city = cityData[selectedProvince]?.find { it.name == cityName }
                    city?.let { onCitySelect(it) }
                }
            )
        }
    }
}

@Composable
fun DropdownSelector(
    selectedText: String,
    showDropdown: Boolean,
    options: List<String>,
    onShowChange: (Boolean) -> Unit,
    onSelect: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onShowChange(!showDropdown) }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedText,
                fontSize = 16.sp,
                color = Color(0xFF333333)
            )

            androidx.compose.material3.Icon(
                imageVector = Icons.Default.ExpandMore,
                contentDescription = "展开",
                modifier = Modifier.size(20.dp)
            )
        }

        if (showDropdown) {
            Column {
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(option) }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option,
                            fontSize = 16.sp,
                            color = Color(0xFF333333)
                        )

                        if (option == selectedText) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "选中",
                                modifier = Modifier.size(20.dp),
                                tint = Color(0xFF00CD00)
                            )
                        }
                    }
                }
            }
        }
    }
}
