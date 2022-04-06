package object.p2pipcam.gridview;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.GridView;

public class RTL_LTR_GridView extends GridView{

	
	float rotaion=0;
	
	public RTL_LTR_GridView(Context context) {
		super(context);
		
		
		
		
	}
	
	
	public RTL_LTR_GridView(Context context, AttributeSet attrs) {
		
		
		
		super(context, attrs);
		
		Matrix matrix=RTL_LTR_GridView.this.getMatrix();
		
		float[] f = new float[9];
		matrix.getValues(f);
		
		rotaion=RTL_LTR_GridView.this.getRotationY();
		
		
		this.getChoiceMode();
		
		this.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
		
		if(rotaion==180)
		{
			this.setRotation(0);
		}
		
		android.view.ViewGroup.LayoutParams params=generateDefaultLayoutParams();
		
		
		
	    }

	
	 public RTL_LTR_GridView(Context context, AttributeSet attrs, int defStyleAttr) {
		 super(context, attrs, defStyleAttr, 0);
	    }
	 
	 
	 @Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		
		
	}
	 
	
}
