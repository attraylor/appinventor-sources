// -*- mode: java; c-basic-offset: 2; -*-

package com.google.appinventor.components.runtime;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import com.google.appinventor.components.common.YaVersion;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(version = YaVersion.GRAPH_COMPONENT_VERSION,
category = ComponentCategory.USERINTERFACE,
description = "A graph component encapsulating functionality from the D3 JavaScript library.")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class Graph extends AndroidViewComponent{
  public static final String TAG = "GRAPH";
  private final WebView webview;
  private Form form;
  
  /**
   * Creates a new Graph component.
   * @param container  container the component will be placed in.
   */
  public Graph(ComponentContainer container) {
    super(container);
    this.form = container.$form();
    webview = new WebView(container.$context());

    webview.getSettings().setJavaScriptEnabled(true);
    webview.setFocusable(true);
    // adds a way to send strings to the JavaScript
    webview.addJavascriptInterface(new WebViewInterface(form), "AppInventorGraph");
    container.$add(this);
    webview.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
              if (!v.hasFocus()) {
                v.requestFocus();
              }
              break;
          }
          return false;
        }
      });
    loadGraph();
    Width(LENGTH_FILL_PARENT);
    Height(LENGTH_FILL_PARENT);
  }
  
  private void loadGraph() {
	  String graph = "<head>\r\n" + 
	  		"  <script src=\"http://d3js.org/d3.v3.min.js\"></script>\r\n" + 
	  		"  <script src=\"http://dimplejs.org/dist/dimple.v2.1.6.min.js\"></script>\r\n" + 
	  		"</head>\r\n" + 
	  		"<body>\r\n" + 
	  		"  <script type=\"text/javascript\">\r\n" + 
	  		"    var svg = dimple.newSvg(\"body\", 400, 200);\r\n" + 
	  		"    var data = [\r\n" + 
	  		"      { \"Word\":\"Hello\", \"Awesomeness\":2000 },\r\n" + 
	  		"      { \"Word\":\"World\", \"Awesomeness\":3000 }\r\n" + 
	  		"    ];\r\n" + 
	  		"    var chart = new dimple.chart(svg, data);\r\n" + 
	  		"    chart.addCategoryAxis(\"x\", \"Word\");\r\n" + 
	  		"    chart.addMeasureAxis(\"y\", \"Awesomeness\");\r\n" + 
	  		"    chart.addSeries(null, dimple.plot.bar);\r\n" + 
	  		"    chart.draw();\r\n" + 
	  		"  </script>\r\n" + 
	  		"</body>";
	  webview.loadDataWithBaseURL(null, graph, "text/html", "utf-8", null);
  }
  
  @Override
  public View getView() {
    return webview;
  }
  
 //Components don't normally override Width and Height, but we do it here so that
 // the automatic width and height will be fill parent.
 @Override
 @SimpleProperty()
 public void Width(int width) {
   if (width == LENGTH_PREFERRED) {
     width = LENGTH_FILL_PARENT;
   }
   super.Width(width);
 }

 @Override
 @SimpleProperty()
 public void Height(int height) {
   if (height == LENGTH_PREFERRED) {
     height = LENGTH_FILL_PARENT;
   }
   super.Height(height);
 }
  /**
   * Allows the setting of properties to be monitored from the JavaScript
   * in the WebView.
   */
  public class WebViewInterface {
    Form webViewForm;
    
	/** Instantiate the interface and set the context */
	WebViewInterface(Form webViewForm) {
	  this.webViewForm = webViewForm;
	}
	    
	/**
	 * Method to be invoked from JavaScript in the WebView
	 * @param errorNumber the error number to dispatch
	 */
	@JavascriptInterface
	public void dispatchError(int errorNumber) {
	  Log.d(TAG, "Error triggered on graph with errorNumber: " + errorNumber);
	  webViewForm.dispatchErrorOccurredEvent(webViewForm, "dispatchError", errorNumber);
    }
  }
}

