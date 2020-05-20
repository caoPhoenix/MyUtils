## Images To Video

这是一个小工具，用于将一系列图片按照指定的帧率和分辨率生成视频。会自动将图片分辨率等比例缩放至指定分辨率，并以黑边填充。

This is a small util for generating video from a series of images at a specified frame rate and resolution. The picture resolution will be automatically scaled to the specified resolution and filled with black borders.

## Usage

0. 依赖`numpy`和`cv2`：
```shell
pip install numpy
pip install opencv-python
```

1. 下载本代码到本地；
2. 在命令行运行本代码，并指定参数：
```shell
python imgs2video.py -i D:\images\
python imgs2video.py -i D:\images\ -o test.mp4 -f 0.5 -r 1920 1080 -s
```

* 参数介绍：
```
--input, -i: 输入图片的路径，必须参数；
--output, -o: 输出视频的名字，默认 out.mp4；
--fps, -f: 指定的帧率，类型浮点数，默认 1.0；
--resolution, -r: 指定视频的分辨率，类型两个整数，默认 1280 720；
--save, -s: 是否保存图片转化分辨率之后的中间结果，默认不保存。
```
