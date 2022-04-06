package object.p2pipcam.customComponent;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Gallery;
import com.livixa.client.ShowLocalPictureActivity;

public class MyGallery extends Gallery {
	private GestureDetector gestureScanner;
	private MyLocalPicImageView imageView;

	public MyGallery(Context context) {
		super(context);

	}

	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);

		gestureScanner = new GestureDetector(new MySimpleGesture());
		this.setOnTouchListener(new OnTouchListener() {

			float baseValue;
			float originalScale;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				View view = MyGallery.this.getSelectedView();
				if (view instanceof MyLocalPicImageView) {
					imageView = (MyLocalPicImageView) view;

					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						baseValue = 0;
						originalScale = imageView.getScale();
						Log.d("tg", "originalScale:" + originalScale);
					}
					if (event.getAction() == MotionEvent.ACTION_MOVE) {
						if (event.getPointerCount() == 2) {
							float x = event.getX(0) - event.getX(1);
							float y = event.getY(0) - event.getY(1);
							float value = (float) Math.sqrt(x * x + y * y);// ��������ľ���
							if (baseValue == 0) {
								baseValue = value;
							} else {
								float scale = value / baseValue;// ��ǰ�����ľ��������ָ����ʱ�����ľ��������Ҫ���ŵı�����
								Log.d("tg", "MyGallery onTouch scale:" + scale);
								imageView.zoomTo(originalScale * scale, x
										+ event.getX(1), y + event.getY(1));

							}
						}
					}
				}
				return false;
			}

		});
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		View view = MyGallery.this.getSelectedView();
		if (view instanceof MyLocalPicImageView) {
			imageView = (MyLocalPicImageView) view;

			float v[] = new float[9];
			Matrix m = imageView.getImageMatrix();
			m.getValues(v);
			// ͼƬʵʱ��������������
			float left, right;
			// ͼƬ��ʵʱ����
			float width, height;
			width = imageView.getScale() * imageView.getImageWidth();
			height = imageView.getScale() * imageView.getImageHeight();
			// һ���߼�Ϊ�ƶ�ͼƬ�ͻ���gallery�������߼������û����������˽�ķǳ��������Ķ����µĴ���ǰ����˼������������
			if ((int) width <= ShowLocalPictureActivity.screenWidth
					&& (int) height <= ShowLocalPictureActivity.screenHeight)// ���ͼƬ��ǰ��С<��Ļ��С��ֱ�Ӵ������¼�
			{
				super.onScroll(e1, e2, distanceX, distanceY);
			} else {
				left = v[Matrix.MTRANS_X];
				right = left + width;
				Rect r = new Rect();
				imageView.getGlobalVisibleRect(r);

				if (distanceX > 0)// ���󻬶�
				{
					if (r.left > 0) {// �жϵ�ǰImageView�Ƿ���ʾ��ȫ
						super.onScroll(e1, e2, distanceX, distanceY);
					} else if (right < ShowLocalPictureActivity.screenWidth) {
						super.onScroll(e1, e2, distanceX, distanceY);
					} else {
						imageView.postTranslate(-distanceX, -distanceY);
					}
				} else if (distanceX < 0)// ���һ���
				{
					if (r.right < ShowLocalPictureActivity.screenWidth) {
						super.onScroll(e1, e2, distanceX, distanceY);
					} else if (left > 0) {
						super.onScroll(e1, e2, distanceX, distanceY);
					} else {
						imageView.postTranslate(-distanceX, -distanceY);
					}
				}

			}

		} else {
			super.onScroll(e1, e2, distanceX, distanceY);
		}
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gestureScanner.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			// �ж����±߽��Ƿ�Խ��
			View view = MyGallery.this.getSelectedView();
			if (view instanceof MyLocalPicImageView) {
				imageView = (MyLocalPicImageView) view;
				float width = imageView.getScale() * imageView.getImageWidth();
				float height = imageView.getScale()
						* imageView.getImageHeight();
				if ((int) width <= ShowLocalPictureActivity.screenWidth
						&& (int) height <= ShowLocalPictureActivity.screenHeight)// ���ͼƬ��ǰ��С<��Ļ��С���жϱ߽�
				{
					break;
				}
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	private boolean isFirst = true;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (isFirst) {
				Log.d("tgg", "�����¼�");
				MotionEvent event = (MotionEvent) msg.obj;

				if (myGalleryEvent != null) {
					myGalleryEvent.myTouch(event);
				}
			} else {
				Log.d("tgg", "ȡ���¼�");
			}
		}
	};

	private class MySimpleGesture extends SimpleOnGestureListener {
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			Log.d("tgg", "isFirst");
			isFirst = true;
			Message msg = handler.obtainMessage();
			msg.obj = e;
			handler.sendMessageDelayed(msg, 300);
			return super.onSingleTapUp(e);
		}

		// �����µĵڶ���Touch downʱ����
		public boolean onDoubleTap(MotionEvent e) {
			isFirst = false;
			Log.d("tgg", "OnDublieTap");
			View view = MyGallery.this.getSelectedView();
			if (view instanceof MyLocalPicImageView) {
				imageView = (MyLocalPicImageView) view;
				if (imageView.getScale() > imageView.getScaleRate()) {

					imageView.zoomTo(imageView.getScaleRate(),
							ShowLocalPictureActivity.screenWidth / 2,
							ShowLocalPictureActivity.screenHeight / 2, 200f);
				} else {
					imageView.zoomTo(1.0f,
							ShowLocalPictureActivity.screenWidth / 2,
							ShowLocalPictureActivity.screenHeight / 2, 200f);
				}

			} else {

			}
			return true;
		}
	}

	public void setMyTouch(MyGalleryEvent myGalleryEvent) {
		this.myGalleryEvent = myGalleryEvent;
	}

	private MyGalleryEvent myGalleryEvent = null;

	public interface MyGalleryEvent {
		abstract public void myTouch(MotionEvent event);
	}

}
