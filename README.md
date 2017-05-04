# AnimatorButton
android 自定义控件 动画按钮
* thanks for [lygttpod/AndroidCustomView](https://github.com/lygttpod/AndroidCustomView)
* thanks for [ Android 动画之集合动画AnimatorSet](http://blog.csdn.net/qqxiaoqiang1573/article/details/53112584)

模仿[Android自定义动画酷炫的提交按钮](http://www.jianshu.com/p/3eb9777f6ab7)的一次实战。

效果还不错。代码还需要优化。


已经将其做成lib库，可以直接使用：

gradle:
  1. in project build.gradle
    <pre>
      <code>
        allprojects {
          repositories {
            ...
            maven { url 'https://jitpack.io' }
          }
        }
      </code>
    </pre>
    
   2. in app module build.gradle
   <pre>
    <code>
      dependencies {
          compile 'com.github.pythoncat1024:AnimatorButton:0.0.1'
      }
    </code>
   </pre>
   
   3. in xml 
   
   <code>
    
    
      <com.python.cat.animatorbutton.AnimatorButton
        android:id="@+id/btn_animate"
        android:layout_width="200dp"
        android:layout_height="50dp" />
    
    
   </code>
  
  
  
   4. in Activity
   
   <pre>
    <code>
      ab = (AnimatorButton) findViewById(R.id.btn_animate);
      ab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ab.start(new Runnable() {
                  @Override
                  public void run() {
                      Toast.makeText(getApplication(), "ok", Toast.LENGTH_SHORT).show();
                  }
              });
          }
      });
    </code>
   </pre>
