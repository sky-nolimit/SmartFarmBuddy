# 智能农苑助手

基于 Android 平台的植物养护管理应用，帮助用户科学养植。

## 功能特性

- **植物手册**：收录22种植物的详细介绍和养护指南
- **天气查询**：获取实时天气信息，辅助养护决策
- **养护提醒**：智能计算浇水、松土、施肥时间
- **养护操作**：一键完成养护记录，数据自动保存
- **滑动交互**：支持左右滑动切换标签页

## 技术栈

- **语言**：Kotlin
- **UI框架**：Jetpack Compose (Material3)
- **架构**：MVVM + LiveData
- **数据存储**：SharedPreferences

## 项目结构

```
app/src/main/
├── java/com/example/smartfarmbuddy/
│   ├── data/              # 数据层
│   ├── ui/screens/        # 页面组件
│   ├── ui/components/     # 通用组件
│   └── ui/viewmodel/      # 视图模型
└── res/                   # 资源文件
    ├── drawable/          # 植物图片
    └── values/            # 配置文件
```

## 运行方式

1. 使用 Android Studio 打开项目
2. 连接 Android 设备或启动模拟器
3. 点击 Run 按钮运行应用

## 页面说明

| 页面 | 说明               |
| -- | ---------------- |
| 手册 | 展示植物分类和列表，点击查看详情 |
| 首页 | 显示天气、时间和养护状态     |
| 设置 | 配置植物选择、提醒开关和城市信息 |

## 许可证

MIT License
