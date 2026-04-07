# OnlineShoppingStore

一个使用 `RecyclerView` 展示服装列表的 Android 示例项目。

## 已实现功能

- 主界面灰色背景（`main_bg_gray`）。
- 10 个服装商品（两列网格）。
- 前 3 个商品点击后跳转详情页。
- 使用联网下载后的本地图片资源（`app/src/main/res/drawable-nodpi/cloth_01.jpg` ~ `cloth_10.jpg`）。
- 支持上下滚动浏览。

## 运行方式

1. 使用 Android Studio 打开项目根目录：`OnlineShoppingStore`。
2. 等待 Gradle 同步完成。
3. 启动一个可用设备：
   - 真机（USB 调试）或
   - 模拟器（AVD）
4. 点击运行 `app`。

## 命令行构建（可选）

```powershell
.\gradlew.bat :app:assembleDebug
```

APK 输出目录：

`app/build/outputs/apk/debug/`

## 说明

- 图片来自在线抓取并保存到本地资源目录，避免运行时网络加载失败导致白图。
- 如果你想替换图片，只需用同名文件覆盖 `drawable-nodpi` 中对应图片即可。

