# IntensifyGridView
![预览](screenshot/merge.jpg)

# Sample



``` xml
<me.kareluo.intensify.gridview.IntensifyGridView
    android:id="@+id/igv_grid"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:divider="@drawable/spacer_bar"
    android:orientation="vertical"
    app:blockSize="match_parent"
    app:blockType="square"
    app:ellipsize="end"
    app:horizontalSpacing="1dp"
    app:maxLines="3"
    app:spacingGravity="share"
    app:spanCount="3"
    app:verticalSpacing="1dp" />
```

- 带有一个额外布局:

``` xml
<me.kareluo.intensify.gridview.IntensifyGridView
   android:id="@+id/igv_grid"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   android:divider="@drawable/spacer_bar"
   android:orientation="vertical"
   app:blockSize="match_parent"
   app:blockType="square"
   app:ellipsize="end"
   app:extraCount="1"
   app:horizontalSpacing="1dp"
   app:spacingGravity="share"
   app:spanCount="5"
   app:verticalSpacing="1dp" />
```


