from django.urls import path
from . import views

from django.conf import settings
from django.conf.urls.static import static
urlpatterns = [
    path('', views.index,name="index"),
    path('index', views.index,name="index"),
     path("login",views.Login,name="login"),
    path("admin",views.admin,name="admin"),
    path("staff",views.staff,name='staff'),
    path("Logout",views.Logout,name='Logout'),
    path("Profile",views.Profile,name='Profile'),
     path("Privacy",views.Privacy,name='Privacy'), 
      path("Appoint_district",views.Appoint_district,name='Appoint_district'),
     path("list_district",views.list_district,name='list_district'),
     path("remove_district",views.remove_district,name='remove_district'),
    path("New_pandamic",views.New_pandamic,name='New_pandamic'),
     path("list_pandamic",views.list_pandamic,name='list_pandamic'),
     path("remove_pandamic",views.remove_pandamic,name='remove_pandamic'),
      path("activate_pandamic",views.activate_pandamic,name='activate_pandamic'),
       path("deactivate_pandamic",views.deactivate_pandamic,name='deactivate_pandamic'),
 path("alerts",views.alerts,name='alerts'), 
 path("list_alert",views.list_alert,name='list_alert'),
 path("remove_alert",views.remove_alert,name='remove_alert'), 
 path("activate_alert",views.activate_alert,name='activate_alert'),
  path("deactivate_alert",views.deactivate_alert,name='deactivate_alert'), 
path("new_shop",views.new_shop,name='new_shop'),
path("current_shop",views.current_shop,name='current_shop'), 
 path("remove_shop",views.remove_shop,name='remove_shop'),
  path("approve_shop",views.approve_shop,name='approve_shop'),
  path("remove_shop1",views.remove_shop1,name='remove_shop1'),
   path("list_ashaworker",views.list_ashaworker,name='list_ashaworker'),
    path("list_doctor",views.list_doctor,name='list_doctor'),
     path("remove_emp",views.remove_emp,name='remove_emp'),
      path("remove_emp1",views.remove_emp1,name='remove_emp1'),
       path("list_user",views.list_user,name='list_user'),
        path("remove_usr",views.remove_usr,name='remove_usr'),
       path("list_user1",views.list_user1,name='list_user1'),
        path("remove_usr1",views.remove_usr1,name='remove_usr1'),
         path("approve_usr",views.approve_usr,name='approve_usr'), 
         path("Appoint_staff",views.Appoint_staff,name='Appoint_staff'),
         path("shop",views.shop,name='shop'),
         path("Add_stock",views.Add_stock,name='Add_stock'),
         path("list_stock",views.list_stock,name='list_stock'),
         path("remove_stock",views.remove_stock,name='remove_stock'),
         path("shopNewOrders",views.shopNewOrders,name='shopNewOrders'),
         path("manage_location",views.manage_location,name='manage_location'),
         path("remove_loc",views.remove_loc,name='remove_loc'),
         path("notifications",views.notifications,name='notifications'),
        path("remove_notif",views.remove_notif,name='remove_notif'),
        path("kit_request",views.kit_request,name='kit_request'),
        path("Reject_kit",views.Reject_kit,name='Reject_kit'),

        path("assign_work",views.assign_work,name='assign_work'),
        path("kit_history",views.kit_history,name='kit_history'),
        path("list_alert",views.list_alert,name='list_alert'),
        path("list_alert",views.list_alert,name='list_alert'),
        path("list_alert",views.list_alert,name='list_alert'),
        path("list_alert",views.list_alert,name='list_alert'),
        path("list_alert",views.list_alert,name='list_alert'),
        path("getLoc",views.getLoc,name='getLoc'),
        path("registerMedShop",views.registerMedShop,name='registerMedShop'),
        path("distServiceRequests",views.distServiceRequests,name='distServiceRequests'),
        path("assignService",views.assignService,name='assignService'),
        path("rejectService",views.rejectService,name='rejectService'),
        path("distServiceHistory",views.distServiceHistory,name='distServiceHistory'),
        path("setOrderDelivered",views.setOrderDelivered,name='setOrderDelivered'),
        path("shopOrderHistory",views.shopOrderHistory,name='shopOrderHistory'),


path("app_login",views.app_login,name='app_login'),
path("app_register",views.app_register,name='app_register'),
path("app_getdistrict",views.app_getdistrict,name='app_getdistrict'),
path("app_getlocation",views.app_getlocation,name='app_getlocation'),
path("app_editPassword",views.app_editPassword,name='app_editPassword'),
path("app_getprofilework",views.app_getprofilework,name='app_getprofilework'),
path("app_getProfile",views.app_getProfile,name='app_getProfile'),
path("app_getashalist",views.app_getashalist,name='app_getashalist'),
path("app_getdoctorlist",views.app_getdoctorlist,name='app_getdoctorlist'),

path("app_getAlert",views.app_getAlert,name='app_getAlert'),
path("app_requestKit",views.app_requestKit,name='app_requestKit'),
path("app_getCategory",views.app_getCategory,name='app_getCategory'),
path("app_getKitDetails",views.app_getKitDetails,name='app_getKitDetails'),
path("app_ashaKitRequests",views.app_ashaKitRequests,name='app_ashaKitRequests'),
path("app_issueKitRequest",views.app_issueKitRequest,name='app_issueKitRequest'),
path("app_ashaVerifiedKitDetails",views.app_ashaVerifiedKitDetails,name='app_ashaVerifiedKitDetails'),
path("app_getIssuedKitDetails",views.app_getIssuedKitDetails,name='app_getIssuedKitDetails'),
path("app_uploadImage",views.app_uploadImage,name='app_uploadImage'),
path("app_docGetCases",views.app_docGetCases,name='app_docGetCases'),
path("app_docAddReport",views.app_docAddReport,name='app_docAddReport'),
path("app_docCompletedTestCases",views.app_docCompletedTestCases,name='app_docCompletedTestCases'),
path("app_docTodayPositiveCases",views.app_docTodayPositiveCases,name='app_docTodayPositiveCases'),
path("app_getShopList",views.app_getShopList,name='app_getShopList'),
path("app_getMedicines",views.app_getMedicines,name='app_getMedicines'),
path("app_addOrder",views.app_addOrder,name='app_addOrder'),
path("app_getCartItems",views.app_getCartItems,name='app_getCartItems'),
path("app_userRemoveItem",views.app_userRemoveItem,name='app_userRemoveItem'),
path("app_userPlaceOrder",views.app_userPlaceOrder,name='app_userPlaceOrder'),
path("app_userOrderDetails",views.app_userOrderDetails,name='app_userOrderDetails'),
path("app_userSendServiceReq",views.app_userSendServiceReq,name='app_userSendServiceReq'),
path("app_userGetServiceBookings",views.app_userGetServiceBookings,name='app_userGetServiceBookings'),
path("app_getAshaServiceRequests",views.app_getAshaServiceRequests,name='app_getAshaServiceRequests'),
path("app_ashaCompleteService",views.app_ashaCompleteService,name='app_ashaCompleteService'),
path("app_getAshaServiceHistory",views.app_getAshaServiceHistory,name='app_getAshaServiceHistory'),
path("app_getUserChat",views.app_getUserChat,name='app_getUserChat'),
path("app_userSendMessage",views.app_userSendMessage,name='app_userSendMessage'),
path("app_docGetPublicList",views.app_docGetPublicList,name='app_docGetPublicList'),
path("app_getDocChat",views.app_getDocChat,name='app_getDocChat'),
path("app_docSendMessage",views.app_docSendMessage,name='app_docSendMessage'),
path("app_uploadAadhaar",views.app_uploadAadhaar,name='app_uploadAadhaar'),
path("app_uploadPhoto",views.app_uploadPhoto,name='app_uploadPhoto'),
path("app_getTPR",views.app_getTPR,name='app_getTPR'),
path("app_getShopList1",views.app_getShopList1,name='app_getShopList1'),

]
if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)