# IntensifyGridView
![预览](screenshot/merge.jpg)

# Usage
- 省略布局（如QQ空间图片大于9张时最后一张的图片显示样式特殊），形式类型TextView的省略样式，可以指定省略位置(前，后，无)
- 额外布局（如设置最大长度为9，额外布局为1，那么在长度小于等于9时会出现额外布局，大于等于9时额外布局消失）
- 自动布局（根据设置的Block的尺寸进行自动布局，类似于`GridView`的`auto_fit`）
- 设置间隙的宽度，以及多余间隙的位置(前留白，后留白，均分)
- 设置间隙的drawable
- 最大行数，或者最大长度
- 指定Block的形状(长方形和正方形)

# Sample

* 当autoFit为true时spanCount便会无效，这时spanCount是由blockWidth决定的，当autoFit为false时，需要设置spanCount来确定列数

``` xml
<me.kareluo.intensify.gridview.IntensifyGridView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:divider="[color|drawable]"
    app:autoFit="[boolean]"
    app:blockSize="[match_parent|wrap_content|dimension]"
    app:blockWidth="[match_parent|wrap_content|dimension]"
    app:blockHeight="[match_parent|wrap_content|dimension]"
    app:blockType="[square|rectangle]"
    app:ellipsize="[none|start|end]"
    app:horizontalSpacing="[dimension["
    app:verticalSpacing="[dimension]"
    app:maxLines="[integer]"
    app:maxLength="[integer]"
    app:spacingGravity="[share|start|end]"
    app:spanCount="[integer]"
    app:extraCount="[single|integer]"
    app:spacer="[color|drawable]"/>
```

``` java
// Adapter 继承 IntensifyGridAdapter
private class TestAdapter extends IntensifyGridAdapter<DemoViewHolder> {

   public TestAdapter(@NonNull IntensifyGridView intensifyGridView) {
       super(intensifyGridView);
   }
   
   @Override
   protected void onBindCommonViewHolder(DemoViewHolder holder, int position) {
   	 // 绑定普通View
       holder.update(mResIds[position]);
   }
   
   @Override
   protected void onBindEllipsizeViewHolder(DemoViewHolder holder, int position) {
       // 绑定省略布局View
       holder.update(position, getEllipsizeCount());
   }
   
   @Override
   protected void onBindExtraViewHolder(DemoViewHolder holder, int position) {
       // 绑定额外布局View
       super.onBindExtraViewHolder(holder, position);
   }
	 
   @Override
   public DemoViewHolder onCreateCommonViewHolder(ViewGroup parent, int type) {
       // 创建普通Holder
       return new DemoViewHolder(new ImageItemView(getBaseContext()));
   }
   
   @Override
   public DemoViewHolder onCreateEllipsizeViewHolder(ViewGroup parent) {
       // 创建省略布局Holder
       return new DemoViewHolder(new ImageItemView(getBaseContext()));
   }
   
   @Override
   public DemoViewHolder onCreateExtraViewHolder(ViewGroup parent) {
       // 创建额外布局Holder
       return new DemoViewHolder(new ImageItemView(getBaseContext()));
   }

   @Override
   public int getCount() {
       return mResIds.length;
   }
}
```

# License

``` license
Copyright 2015 kareluo.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

