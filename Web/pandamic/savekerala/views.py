from email import message
from multiprocessing.connection import wait
from unicodedata import name
from django.shortcuts import render,redirect
import datetime
from datetime import date
from datetime import datetime
from django.core.files.storage import FileSystemStorage
from django.db.models import Q
from .models import login as log,district as dst,category as cat,alerts as alt
from .models import medicalshop as shp,worker as wrk,public as usr,locations as loc,chat
from .models import medicinestock as stk,notification as nots,kitrequest as kit,cases,assisatnce as assist,order,item,bank,servicerequest as sreq
# Create your views here.

def index(request):
    dist = dst.objects.all()

    tpr = 0
    count = 0
    for d in dist:
        count = count+ 1
        tpr = tpr + float(d.getTPR)
        
    result = tpr+(count/100)
    print(result)

    alert = alt.objects.all().order_by('-alertsid')[:3]

    return render(request,"index.html",{"dist":dist, "result": result, "alert": alert})

def getLoc(request):
    if request.method == 'GET':
        id = request.GET['x']
        data = loc.objects.filter(distict=id).all()
        result = "<option value='' selected disabled hidden>- - SELECT LOCATION - -</option>"
        for d in data:
            result += "<option value ='"+str(d.location_id)+"'>"+str(d.location)+"</option>"

        return HttpResponse(result) # Sending an success response

def registerMedShop(request):
    if request.POST:
        sname = request.POST['name']
        owner = request.POST['owner']
        phone = request.POST['phone']
        email = request.POST['email']
        district = request.POST['district']
        location = request.POST['location']
        license = request.POST['license']
        lcopy = request.FILES['lcopy']
        hours = request.POST['hours']
        uname = request.POST['uname']
        pswd = request.POST['pass']

        dist = dst.objects.get(district_id=district)
        loca = loc.objects.get(location_id=location)

        dat=log.objects.create(username=uname,password=pswd,role="shop")
        data=log.objects.last()
        shp.objects.create(shopname=sname,licenseno=license,ownerno=owner,contactno=phone,email=email,workinghrs=hours,licensecpy=lcopy,medstatus="waiting",location=loca,login=data,distict=dist)
        msg="added Successfully"    
    response = redirect('/index')
    return response
    
def Privacy(request):
    msg=""
    if request.POST:
        t1=request.POST["t1"]
        t2=request.POST["t2"]
        id=request.session["id"]
        data=log.objects.get(logid=id)
        if data.password==t1:
            msg="sucessfully updated"
            log.objects.filter(logid=id).update(password=t2)
        else:
            msg="invalid current password"

    returnpage="admin.html"
    if(request.session.get('role', ' ')=="district"):
        returnpage="staff.html"
    elif request.session["role"] =="shop":
        returnpage="shop.html"
    return render(request, "privacy.html",{"role":returnpage,"msg":msg})

def admin(request):
    if(request.session.get('role', ' ')=="admin"):
        data = dst.objects.all()
        return render(request,"adminhome.html",{"data":data})
        
    else:
        response = redirect('/index'+"?msg=session expired login again")
        return response

def staff(request):
    if(request.session.get('role', ' ')=="district"):
        user = dst.objects.get(login = request.session['id'])
        data = dst.objects.all()
        return render(request,"staffhome.html",{"user": user, "data": data})
    else:
        response = redirect('/index'+"?msg=session expired login again")
        return response

def shop(request):
    if(request.session.get('role', ' ')=="shop"):
        user = shp.objects.get(login = request.session['id'])
        data = dst.objects.all()
        return render(request,"shophome.html",{"user": user, "data": data})
    else:
        response = redirect('/index'+"?msg=session expired login again")
        return response

def Login(request):
    if request.POST:
        user = request.POST["username"]
        password = request.POST["password"]
        try:
            data = log.objects.get(username=user, password=password)
            if (data.role == "admin"):
                request.session['username'] = data.username
                request.session['role'] = data.role
                request.session['id'] = data.logid
                response = redirect('/admin')
                return response
            elif (data.role == "district"):
                request.session['username'] = data.username
                request.session['role'] = data.role
                request.session['id'] = data.logid
                response = redirect('/staff')
                return response
            elif (data.role == "shop"):
                dr=shp.objects.get(login=data)
                if dr.medstatus == 'approved':
                    request.session['username'] = data.username
                    request.session['role'] = data.role
                    request.session['id'] = data.logid
                    response = redirect('/shop')
                    return response
                else :
                    return render(request, "index.html", {"msg": "waiting for approval."})
            else:
                return render(request, "index.html", {"msg": "invalid account Details"})
        except:
            return render(request, "index.html", {"msg": "invalid user name or password "})
    else:
        response = redirect('/index')
        return response

def Profile(request):
    msg=""
    ids=request.session["id"]
    if request.POST:
        if request.session["role"] =="district":
            t2 = request.POST["t2"]
            t3 = request.POST["t3"]
            t4 = request.POST["t4"]
            stf.objects.filter(Staff_logid=ids).update(Staff_address=t2,Staff_email=t3,Staff_phone=t4)
        
    
    if request.session["role"] =="district":
        data1=dst.objects.get(login=ids)
        returnpage="StaffProfile.html"
    elif request.session["role"] =="shop":

        data1=shp.objects.get(login=request.session['id'])
        returnpage="ShopProfile.html"
    else:
        response = redirect('/index'+"?msg=session expired login again")
        return response
    return render(request,returnpage,{"msg":msg,"data":data1})

def list_ashaworker(request):
    datad=dst.objects.get(login=request.session["id"])
    data=wrk.objects.filter(distict=datad,emptype="asha").all()
    return render(request,"list_ashaworker.html",{"data":data})

def list_doctor(request):
    datad=dst.objects.get(login=request.session["id"])
    data=wrk.objects.filter(distict=datad,emptype="doc").all()
    return render(request,"list_doctor.html",{"data":data})

def list_user(request):
    datad=dst.objects.get(login=request.session["id"])
    data=usr.objects.filter(distict=datad,pubstatus="waiting").all()    
    return render(request,"list_user.html",{"data":data})

def list_user1(request):
    datad=dst.objects.get(login=request.session["id"])
    data=usr.objects.filter(distict=datad,pubstatus="approved").all()    
    return render(request,"list_user1.html",{"data":data})

def remove_usr(request):
    id=request.GET["id"]
    usr.objects.filter(pubid=id).delete()
    return redirect("list_user")

def approve_usr(request):
    id=request.GET["id"]
    usr.objects.filter(pubid=id).update(pubstatus="approved")
    return redirect("list_user")

def remove_usr1(request):
    id=request.GET["id"]
    usr.objects.filter(pubid=id).delete()
    return redirect("list_user1")

def remove_emp(request):
    id=request.GET["id"]
    wrk.objects.filter(workerid=id).delete()
    return redirect('list_ashaworker')    

def remove_emp1(request):
    id=request.GET["id"]
    wrk.objects.filter(workerid=id).delete()
    return redirect('list_doctor')   

def new_shop(request):
    datad=dst.objects.get(login=request.session["id"])
    data=shp.objects.filter(medstatus="waiting",distict=datad).all()
    return render(request,"new_shops.html",{"data":data})

def current_shop(request):
    datad=dst.objects.get(login=request.session["id"])
    data=shp.objects.filter(medstatus="approved",distict=datad).all()
    return render(request,"current_shops.html",{"data":data})

def remove_shop(request):
    id=request.GET["id"]
    shp.objects.filter(medshop_id=id).delete()
    return redirect('new_shop')    

def remove_shop1(request):
    id=request.GET["id"]
    shp.objects.filter(medshop_id=id).delete()
    return redirect('current_shop')

def approve_shop(request):
    id=request.GET["id"]
    shp.objects.filter(medshop_id=id).update(medstatus="approved")
    return redirect('new_shop')    

def Logout(request):
    try:
        del request.session['id']
        del request.session['role']
        del request.session['username']
        response = redirect("/index?id=logout")
        return response
    except:
        response = redirect("/index?id=logout")
        return response

def Appoint_district(request):
    msg=""
    if request.POST:
        t1=request.POST["t1"]
        t2=request.POST["t2"]
        t3=request.POST["t3"]
        t4=request.POST["t4"]
        t5=request.POST["t5"]
        t6=request.POST["t6"]
        t7=request.POST["t7"]
        dat=log.objects.create(username=t6,password=t7,role="district")
        data=log.objects.last()
        dst.objects.create(district=t1,districtadminnm=t2,districtadmindes=t5,districtadmindcontact=t4,districtadmindemail=t3,login=data)
        msg="added Successfully"    
    return render(request,"Appoint_district.html",{"msg":msg})

def New_pandamic(request):
    msg=""
    if request.POST:
        t1=request.POST["t1"]
        t2=request.POST["t2"]
        t3=request.POST["t3"]
        t4=request.POST["t4"]
        t5=request.POST["t5"]
        t6=request.POST["t6"]
        #

        cat.objects.create(categoryname=t1,categorydesc=t2,categorytype=t3,categorystartdt=t4,categorysymtoms=t5,categoryprecotaions=t6,categorystatus="active")
        msg="added Successfully"    
    return render(request,"Newpandamic.html",{"msg":msg})

def list_district(request):
    msg=""
    if request.POST:
        t1=request.POST["t1"]
        s2=request.POST["s2"]
        s3=request.POST["s3"]
        s4=request.POST["s4"]
        s5=request.POST["s5"]
        dst.objects.filter(district_id=t1).update(districtadminnm=s2,districtadmindemail=s3,districtadmindcontact=s4,districtadmindes=s5)
    data=dst.objects.all()
    return render(request,"list_district.html",{"data":data})

def list_pandamic(request):
    msg=""
    if request.POST:
        t1=request.POST["t1"]
        s1=request.POST["s1"]
        s2=request.POST["s2"]
        s3=request.POST["s3"]
        s4=request.POST["s4"]
        s5=request.POST["s5"]
        s6=request.POST["s6"]
        cat.objects.filter(category_id=t1).update(categoryname=s1,categorydesc=s2,categorytype=s3,categorystartdt=s4,categorysymtoms=s5,categoryprecotaions=s6)
    data=cat.objects.all()
    return render(request,"list_pandamic.html",{"data":data})

def remove_district(request):
    id=request.GET["id"]
    dst.objects.filter(district_id=id).delete()
    return redirect('list_district')

def remove_pandamic(request):
    id=request.GET["id"]
    cat.objects.filter(category_id=id).delete()
    return redirect('list_pandamic')

def activate_pandamic(request):
    id=request.GET["id"]
    cat.objects.filter(category_id=id).update(categorystatus="active")
    return redirect('list_pandamic')

def deactivate_pandamic(request):
    id=request.GET["id"]
    cat.objects.filter(category_id=id).update(categorystatus="inactive")
    return redirect('list_pandamic')

def alerts(request):
    if request.POST:
        t1=request.POST["t1"]
        t2=request.POST["t2"]
        t3=request.POST["t3"]
        t4=request.POST["t4"]
        t5=request.POST["t5"]
        datad=dst.objects.get(district_id=t1)
        datac=cat.objects.get(category_id=t2)
        today = date.today()
        alt.objects.create(alerttype=t3,alertinstrction=t4,category=datac,distict=datad,postdt=today,startdt=t5,alertstatus="active")

    data=alt.objects.all()
    dis=dst.objects.all()
    cats=cat.objects.all()
    return render(request,"alerts.html",{"data":data,"dis":dis,"cats":cats})

def Appoint_staff(request):
    msg=""
    datad=dst.objects.get(login=request.session["id"])
    dataz=loc.objects.filter(distict=datad).all()
    if request.POST:
        t1=request.POST["t1"]
        t2=request.POST["t2"]
        t3=request.POST["t3"]
        t4=request.POST["t4"]
        t5=request.POST["t5"]
        t6=request.FILES["t6"]
        t7=request.POST["t7"]
        t8=request.POST["t8"]
        t9=request.POST["t9"]
        t10=request.POST["t10"]
        datal=loc.objects.get(location_id=t7)
        dat=log.objects.create(username=t8,password=t9,role=t10)
        data=log.objects.last()
        wrk.objects.create(empcode=t1,location=datal,distict=datad,empname=t2,empphoto=t6,empcontact=t5,empemail=t4,empstatus="waiting",empaddress=t3,emptype=t8,login=data)
        #dst.objects.create(district=t1,districtadminnm=t2,districtadmindes=t5,districtadmindcontact=t4,districtadmindemail=t3,login=data)
        msg="added Successfully"    
    return render(request,"Appoint_staff.html",{"msg":msg,"data":dataz})

def list_alert(request):
    msg=""
    if request.POST:
        t1=request.POST["t1"]
       
        s3=request.POST["s3"]
        s4=request.POST["s4"]
        s5=request.POST["s5"]
        
        alt.objects.filter(alertsid=t1).update(alerttype=s3,alertinstrction=s4,postdt=s5)
    data=alt.objects.all()
    dis=dst.objects.all()
    cats=cat.objects.all()
    return render(request,"list_alerts.html",{"data":data,"dis":dis,"cats":cats})

def remove_alert(request):
    id=request.GET["id"]
    alt.objects.filter(alertsid=id).delete()
    return redirect('list_alert')

def activate_alert(request):
    id=request.GET["id"]
    alt.objects.filter(alertsid=id).update(alertstatus="active")
    return redirect('list_alert')

def deactivate_alert(request):
    id=request.GET["id"]
    alt.objects.filter(alertsid=id).update(alertstatus="inactive")
    return redirect('list_alert')

def Add_stock(request):
    msg=""
    if request.POST:
        t1=request.POST["t1"]
        t2=request.POST["t2"]
        t3=request.POST["t3"]
        t4=request.POST["t4"]
        t5=request.FILES["t5"]
        t6=request.POST["t6"]
        datal=log.objects.get(logid=request.session["id"])
        datas=shp.objects.get(login=datal)
        stk.objects.create(medshop=datas,medicinename=t1,medtype=t6,medprice=t2,medqty=t4,meddec=t3,medpic=t5)

        msg="updated successfully"
    return render(request,"Add_stock.html",{"msg":msg})

def list_stock(request):
    msg=""
    if request.POST:
        s4=request.POST["s4"]
        s5=request.POST["s5"]
        t1=request.POST["t1"]
        stk.objects.filter(stock_id=t1).update(medprice=s4,medqty=s5)
        msg="updated"
    datal=log.objects.get(logid=request.session["id"])
    datas=shp.objects.get(login=datal)
    data=stk.objects.filter(medshop=datas).all()
    return render(request,"list_stock.html",{"msg":msg,"data":data})

def shopNewOrders(request):
    msg = ""
    login=log.objects.get(logid=request.session["id"])
    shopid=shp.objects.get(login=login)

    data1 = order.objects.filter(medshop=shopid.medshop_id,status="paid").all()
    # data = []
    # for d in data1:
    #     data2 = item.objects.filter(order=d.orderid).all()
    #     for d2 in data2:
    #         data.append(d2)

    # data=stk.objects.filter(medshop=shopid.medshop_id).all()
    return render(request,"shopNewOrders.html",{"msg":msg,"data":data1})

def setOrderDelivered(request):
    t1 = request.POST['oid']
    order.objects.filter(orderid=t1).update(status="delivered")
    return redirect('shopNewOrders')

def shopOrderHistory(request):
    msg = ""
    login=log.objects.get(logid=request.session["id"])
    shopid=shp.objects.get(login=login)

    data1 = order.objects.filter(medshop=shopid.medshop_id,status="delivered").all()
    
    return render(request,"shopOrderHistory.html",{"msg":msg,"data":data1})


def remove_stock(request):
    id=request.GET["id"]
    stk.objects.filter(stock_id=id).delete()
    return redirect('list_stock')

def manage_location(request):
    msg=""
    datad=dst.objects.get(login=request.session["id"])
    if request.POST:
        t1=request.POST["t1"]
        loc.objects.create(location=t1,distict=datad)
        msg="data saved"

    data=loc.objects.filter(distict=datad).all()
    return render(request,"manage_location.html",{"msg":msg,"data":data})

def remove_loc(request):
    id=request.GET["id"]
    loc.objects.filter(location_id=id).delete()
    return redirect('manage_location')    

def notifications(request):
    msg=""
    if request.POST:
        t1=request.POST["t1"]
        t2=request.POST["t2"]
        t3=request.POST["t3"]
        today = date.today()
        datac=cat.objects.get(category_id=t1)
        msg="Posted to all"
        
        nots.objects.create(notificationtitle=t2,notification=t3,notificdate=today,category=datac)
    cats=cat.objects.all()
    data=nots.objects.all()
    return render(request,"notifications.html",{"msg":msg,"data":data,"cats":cats})

def remove_notif(request):
    id=request.GET["id"]
    nots.objects.filter(notif_id=id).delete()
    return redirect('notifications')

def kit_request(request):
    
    datad=dst.objects.get(login=request.session["id"])
    datas=wrk.objects.filter(distict=datad,emptype="asha").all()
    data= kit.objects.filter(kitstaus="waiting",distict=datad).all()
    return render(request,"list_waitingkitreqest.html",{"data":data,"datas":datas})

def kit_history(request):
    
    datad=dst.objects.get(login=request.session["id"])
    data= kit.objects.exclude(kitstaus="waiting",distict=datad).all()
    return render(request,"list_kithistory.html",{"data":data})
def Reject_kit(request):
    id=request.GET["id"]
    kit.objects.filter(kitid=id).update(kitstaus="rejected")
    return redirect('kit_request')   

def assign_work(request):
    id=request.POST["t1"]
    s4=request.POST["s4"]
    s5=request.POST["s5"]
    datw=wrk.objects.get(workerid=s4)
    kit.objects.filter(kitid=id).update(allotedate=s5,ashaworker=datw,workstatus="assigned")
    return redirect('kit_request') 

def distServiceRequests(request):
    datad=dst.objects.get(login=request.session["id"])
    datas=wrk.objects.filter(distict=datad,emptype="asha").all()
    data= sreq.objects.filter(requeststatus="waiting",district=datad).all()
    return render(request,"distServiceRequests.html",{"data":data,"datas":datas})

def assignService(request):
    id=request.POST["t1"] #serviceid
    s4=request.POST["s4"] #asha
    datw=wrk.objects.get(workerid=s4)
    sreq.objects.filter(serviceid=id).update(worker=datw,requeststatus="assigned")
    return redirect('distServiceRequests') 

def rejectService(request):
    id=request.GET["id"]
    sreq.objects.filter(serviceid=id).update(requeststatus="rejected")
    return redirect('distServiceRequests')   

def distServiceHistory(request):
    datad=dst.objects.get(login=request.session["id"])
    data= sreq.objects.filter(~Q(requeststatus="waiting"),district=datad).all()
    return render(request,"distServiceHistory.html",{"data":data})

##################################################################
###################### Android Api ###############################
##################################################################

from django.http import HttpResponse,JsonResponse
import json
from django.core.files.storage import FileSystemStorage

def app_login(request):
    t1=request.POST["t1"]
    t2=request.POST["t2"]
    msg="try again later"
    c=log.objects.filter(username=t1,password=t2).count()
    if c == 1 : 
        dat=log.objects.get(username=t1,password=t2)
        if(dat.role == "user"):
            du=usr.objects.get(login=dat)
            if du.pubstatus == "waiting":
                msg="not yet verified"
            else :
                msg="ok:"+str(dat.logid)+":user:"+du.pubname+":"+str(du.distict.district_id)+":"+str(du.location.location_id)
        elif(dat.role == "doc"):
            
            dw=wrk.objects.get(login=dat)
            #print(dw)
            
            msg="ok:"+str(dat.logid)+":doc:"+dw.empname +" ["+dw.empcode+"]"+":"+str(dw.distict.district_id)+":"+str(dw.location.location_id)
        elif(dat.role == "asha"):
            dw=wrk.objects.get(login=dat)
            
            msg="ok:"+str(dat.logid)+":asha:"+dw.empname +"["+dw.empcode+"]"+":"+str(dw.distict.district_id)+":"+str(dw.location.location_id)
        else:
            msg="invalid account Details"
    else:
         msg="invalid user name or password"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)
def app_register(request):
    t1=request.POST["t1"]
    t2=request.POST["t2"]
    t3=request.POST["t3"]
    t12=str(request.POST["t12"])
    t4=request.POST["t4"]
    t5=request.POST["t5"]
    t6=request.POST["t6"]
    t7=request.POST["t7"]
    t8=request.POST["t8"]
    t9=request.POST["t9"]
    t10=request.POST["t10"]
    t11=request.POST["t11"]

    dist = dst.objects.get(district_id=t9)
    loca = loc.objects.get(location_id=t10)

    dat=log.objects.create(username=t4,password=t11,role="user")
    data=log.objects.last()
    usr.objects.create(pubname=t1,addharno=t2,pubcontact=t3,pubdob=t12,pubemail=t4,pubaddress=t5,pubjob=t6,pubjobsector=t7,pubjobdescrp=t8,pubstatus="waiting",distict=dist,location=loca,login=data)
    msg="ok:"+str(data.logid)
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_getdistrict(request):

    datar=dst.objects.values("district_id","district","districtadminnm","districtadmindes","districtadmindcontact","districtadmindemail")
    data=json.dumps(list(datar))
    return HttpResponse(data, content_type="application/json")

def app_getlocation(request):
    t1 = request.POST["t1"]
    datax=dst.objects.get(district_id=t1)
    datar=loc.objects.filter(distict=datax).values("location_id","location")
    data=json.dumps(list(datar))
    return HttpResponse(data, content_type="application/json")

def app_getCategory(request):
    datar=cat.objects.filter(categorystatus="active").values("category_id","categoryname","categorydesc","categorytype","categorystartdt","categorysymtoms","categoryprecotaions","categorystatus")
    data=json.dumps(list(datar))
    return HttpResponse(data, content_type="application/json")

def app_editPassword(request):
    t1=request.POST["t1"]
    t2=request.POST["t2"]
    t3=request.POST["t3"]
    data=log.objects.get(logid=t3)
    if data.password==t1:
        msg="ok"
        log.objects.filter(logid=t3).update(password=t2)
    else:
        msg="invalid current password"
    
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_getprofilework(request):
    t1=request.POST["t1"]
    dat=log.objects.get(logid=t1)
    datar=wrk.objects.filter(login=dat).values("workerid","empcode","location","distict","empname","empphoto","empcontact","empemail","empstatus","empaddress","emptype","login")
    data=json.dumps(list(datar))
    #print(data)
    return HttpResponse(data, content_type="application/json")
def app_getProfile(request):
    t1=request.POST["t1"]
    dat=log.objects.get(logid=t1)
    datar=usr.objects.filter(login=dat).values("pubid","pubname","addharno","addharcopy","pubdob","pubcontact","pubemail","pubaddress","pubjob","pubjobsector","pubjobdescrp","pubphoto","pubstatus","location","distict","login")
    data=json.dumps(list(datar))
    #print(data)
    return HttpResponse(data, content_type="application/json")

def app_getdoctorlist(request):
    t1=request.POST["t1"]
    dat=log.objects.get(logid=t1)
    datar=usr.objects.get(login=dat)
    dataw=wrk.objects.filter(distict=datar.distict,emptype="doc").values("workerid","empcode","location","distict","empname","empphoto","empcontact","empemail","empstatus","empaddress","emptype","login")
    data=json.dumps(list(dataw))
    return HttpResponse(data, content_type="application/json")

def app_docGetPublicList(request):
    t1=request.POST["t1"]
    datar=usr.objects.filter(distict=t1,pubstatus="approved").values("pubid","pubname","addharno","addharcopy","pubdob","pubcontact","pubemail","pubaddress","pubjob","pubjobsector","pubjobdescrp","pubphoto","pubstatus","location","distict","login")
    data=json.dumps(list(datar))
    return HttpResponse(data, content_type="application/json")

def app_getashalist(request):
    t1=request.POST["t1"]
    dat=log.objects.get(logid=t1)
    datar=usr.objects.get(login=dat)
    dataw=wrk.objects.filter(distict=datar.distict,emptype="asha").values("workerid","empcode","location","distict","empname","empphoto","empcontact","empemail","empstatus","empaddress","emptype","login")
    data=json.dumps(list(dataw))
    return HttpResponse(data, content_type="application/json")

def app_getAlert(request):
    t1 = request.POST['t1']
    datad = dst.objects.get(district_id=t1)
    data = alt.objects.filter(distict=datad,alertstatus="active").all()

    db = []
    for d in data:
        v = {
            "alertsid":d.alertsid,
            "alerttype":d.alerttype,
            "alertinstrction":d.alertinstrction,
            "postdt":d.postdt,
            "startdt":d.startdt,
            "alertstatus":d.alertstatus,
            "category_id":d.category_id,
            "distict_id":d.distict_id,
            "category":d.category.categoryname
            }
        db.append(v)
    data=json.dumps(list(db))
    return HttpResponse(data, content_type="application/json")

def app_requestKit(request):
    t1 = request.POST['t1']
    t2 = request.POST['t2'] #user
    t3 = request.POST['t3'] #dist
    t4 = request.POST['t4'] #cat
    today = date.today()

    dist = dst.objects.get(district_id=t3)
    user = usr.objects.get(login=t2)
    category = cat.objects.get(category_id=t4)

    kit.objects.create(kitstaus="waiting",requestdt=today,previousrequestdate=t1,workstatus="waiting",public=user,distict=dist,category=category)
    msg="ok"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_getKitDetails(request):
    t1 = request.POST['t1']
    dat=log.objects.get(logid=t1)
    datar=usr.objects.get(login=dat)
    dataw=kit.objects.filter(kitstaus="waiting",public=datar.pubid).all()
    
    db = []
    for d in dataw:
        if(d.workstatus == "waiting"):
            allotedate = ""
            ashaworker = ""
            empname = ""
            empcontact = ""

        else:
            allotedate = d.allotedate
            ashaworker = str(d.ashaworker.workerid)
            empname = str(d.ashaworker.empname)
            empcontact = str(d.ashaworker.empcontact)

        v = {
            "kitid":d.kitid,
            "kitstaus":d.kitstaus,
            "requestdt":d.requestdt,
            "previousrequestdate":d.previousrequestdate,
            "workstatus":d.workstatus,
            "allotedate":allotedate,
            "ashaworker_id":ashaworker,
            "public_id":str(d.public.pubid),
            "distict_id":str(d.distict),
            "category_id":str(d.category),
            "categoryname":str(d.category.categoryname),
            "empname":empname,
            "empcontact":empcontact
            }
        db.append(v)

    data=json.dumps(list(db))
    return HttpResponse(data, content_type="application/json")

def app_getIssuedKitDetails(request):
    t1 = request.POST['t1']
    dat=log.objects.get(logid=t1)
    datar=usr.objects.get(login=dat)
    dataw=kit.objects.filter(~Q(kitstaus="waiting"),public=datar.pubid).all()
    
    db = []
    for d in dataw:
        if(d.kitstaus == "announced"):
            caseid = str(d.getcasedet.caseid)
            casestatus = str(d.getcasedet.casestatus)
            infectionhistory = str(d.getcasedet.infectionhistory)
            infectiontype = str(d.getcasedet.infectiontype)
            casedate = str(d.getcasedet.casedate)

            if(infectiontype == "Positive"):
                message = d.getcasedet.getassistancedet.message
            else:
                message = ""

        else:
            caseid = ""
            casestatus = ""
            infectionhistory = ""
            infectiontype = ""
            casedate =""
            message = ""

        v = {
            "kitid":d.kitid,
            "kitstaus":d.kitstaus,
            "requestdt":d.requestdt,
            "previousrequestdate":d.previousrequestdate,
            "alotqrcode":d.alotqrcode,
            "workstatus":d.workstatus,
            "allotedate":d.allotedate,
            "ashaworker_id":str(d.ashaworker.workerid),
            "public_id":str(d.public.pubid),
            "distict_id":str(d.distict),
            "category_id":str(d.category),
            "categoryname":str(d.category.categoryname),
            "empname":str(d.ashaworker.empname),
            "empcontact":str(d.ashaworker.empcontact),
            "caseid":caseid,
            "casestatus":casestatus,
            "infectionhistory":infectionhistory,
            "infectiontype":infectiontype,
            "casedate":casedate,
            "message":message
            }
        db.append(v)

    data=json.dumps(list(db))
    return HttpResponse(data, content_type="application/json")

def app_ashaKitRequests(request):
    t1 = request.POST['t1']
    dat=log.objects.get(logid=t1)
    datar=wrk.objects.get(login=dat)
    dataw=kit.objects.filter(ashaworker=datar.workerid,workstatus="assigned",kitstaus="waiting").all()
    
    db = []
    for d in dataw:
        
        v = {
            "kitid":d.kitid,
            "kitstaus":d.kitstaus,
            "requestdt":d.requestdt,
            "previousrequestdate":d.previousrequestdate,
            "alotqrcode":d.alotqrcode,
            "workstatus":d.workstatus,
            "allotedate":d.allotedate,
            "ashaworker_id":str(d.ashaworker.workerid),
            "public_id":str(d.public.pubid),
            "distict_id":str(d.distict),
            "category_id":str(d.category),
            "categoryname":str(d.category.categoryname),
            "pubname":d.public.pubname,
            "pubcontact":d.public.pubcontact,
            "casestatus" : "" ,
            "infectiontype" : ""
            }
        db.append(v)

    data=json.dumps(list(db))
    return HttpResponse(data, content_type="application/json")

def app_issueKitRequest(request):
    t1 = request.POST['t1']
    t2 = request.POST['t2']

    kit.objects.filter(kitid=t2).update(alotqrcode=t1,kitstaus="issued")
    msg="ok"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_ashaVerifiedKitDetails(request):
    t1 = request.POST['t1']
    dat=log.objects.get(logid=t1)
    datar=wrk.objects.get(login=dat)
    dataw=kit.objects.filter(~Q(kitstaus="waiting"),ashaworker=datar.workerid).all()
    
    db = []
    for d in dataw:
        
        if (d.kitstaus == "announced"):
            casestatus = str(d.getcasedet.casestatus)
            infectiontype = str(d.getcasedet.infectiontype)
        else:
            casestatus = ""
            infectiontype = ""

        v = {
            "kitid":d.kitid,
            "kitstaus":d.kitstaus,
            "requestdt":d.requestdt,
            "previousrequestdate":d.previousrequestdate,
            "alotqrcode":d.alotqrcode,
            "workstatus":d.workstatus,
            "allotedate":d.allotedate,
            "ashaworker_id":str(d.ashaworker.workerid),
            "public_id":str(d.public.pubid),
            "distict_id":str(d.distict),
            "category_id":str(d.category),
            "categoryname":str(d.category.categoryname),
            "pubname":d.public.pubname,
            "pubcontact":d.public.pubcontact,
            "casestatus" : casestatus ,
            "infectiontype" : infectiontype
            }
        db.append(v)

    data=json.dumps(list(db))
    return HttpResponse(data, content_type="application/json")

def app_uploadImage(request):
    file = request.FILES['filename']
    kid = request.POST['t1']
    t2 = request.POST['t2']
    t3 = request.POST['t3']
    today = date.today()

    dat=log.objects.get(logid=t2)
    datar=usr.objects.get(login=dat)
    dist = dst.objects.get(district_id=t3)
    kitid = kit.objects.get(kitid=kid)
    cases.objects.create(casestatus="waiting",infectionhistory=file,infectiontype="",distict=dist,public=datar,kitrequest=kitid,casedate=today)
    kit.objects.filter(kitid=kid).update(kitstaus="announced")

    msg="ok"
    data=[{'result': msg}]
    return JsonResponse("Uploaded", safe=False)

def app_uploadAadhaar(request):
    file = request.FILES['filename']
    t1 = request.POST['t1']
    fs=FileSystemStorage()
    fs.save("aadhaar/"+file.name,file)
    usr.objects.filter(login=t1).update(addharcopy=file)
    msg="ok"
    data=[{'result': msg}]
    return JsonResponse("Aadhaar uploaded", safe=False)

def app_uploadPhoto(request):
    file = request.FILES['filename']
    t1 = request.POST['t1']
    fs=FileSystemStorage()
    fs.save("photo/"+file.name,file)
    usr.objects.filter(login=t1).update(pubphoto=file)
    msg="ok"
    data=[{'result': msg}]
    return JsonResponse("Photo uploaded", safe=False)

def app_docGetCases(request):
    t1 = request.POST['t1']
    t2 = request.POST['t2']

    dist = dst.objects.get(district_id=t2)
    dataw = cases.objects.filter(casestatus="waiting",distict=dist).all()
    data = []
    for dt in dataw:
        dv={
            "caseid":dt.caseid,
            "casestatus":dt.casestatus,
            "infectionhistory":str(dt.infectionhistory),
            "infectiontype":dt.infectiontype,
            "distict":str(dt.distict.district_id),
            "public_id":str(dt.public.pubid),
            "pubname":dt.public.pubname,
            "pubcontact":dt.public.pubcontact,
            "pubemail":dt.public.pubemail,
            "kitrequest":str(dt.kitrequest.kitid),
            "categoryname":dt.kitrequest.category.categoryname,
            "casedate":dt.casedate
            }
        data.append(dv)
    print(data)
    return JsonResponse(data, safe=False)

def app_docAddReport(request):
    t1 = request.POST['t1'] #caseid
    t2 = request.POST['t2'] #result
    t3 = request.POST['t3'] #msg
    t4 = request.POST['t4'] #logid
    today = date.today()

    dat=log.objects.get(logid=t4)
    datar=wrk.objects.get(login=dat)
  
    ccase = cases.objects.get(caseid=t1)

    cases.objects.filter(caseid=t1).update(infectiontype=t2,casestatus="published")
    if(t3 != ""):
        assist.objects.create(date=today,message=t3,cases=ccase,doctor=datar)
    msg="ok"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_docCompletedTestCases(request):
    t1 = request.POST['t1']
    t2 = request.POST['t2']

    dist = dst.objects.get(district_id=t2)
    dataw = cases.objects.filter(casestatus="published",distict=dist).all()
    data = []
    for dt in dataw:
        dv={
            "caseid":dt.caseid,
            "casestatus":dt.casestatus,
            "infectionhistory":str(dt.infectionhistory),
            "infectiontype":dt.infectiontype,
            "distict":str(dt.distict.district_id),
            "public_id":str(dt.public.pubid),
            "pubname":dt.public.pubname,
            "pubcontact":dt.public.pubcontact,
            "pubemail":dt.public.pubemail,
            "kitrequest":str(dt.kitrequest.kitid),
            "categoryname":dt.kitrequest.category.categoryname,
            "casedate":dt.casedate
            }
        data.append(dv)
    print(data)
    return JsonResponse(data, safe=False)

def app_docTodayPositiveCases(request):
    t1 = request.POST['t1']
    t2 = request.POST['t2']
    today = date.today()


    dist = dst.objects.get(district_id=t2)
    dataw = cases.objects.filter(casestatus="published",distict=dist,infectiontype="Positive",casedate=today).all()
    data = []
    for dt in dataw:
        dv={
            "caseid":dt.caseid,
            "casestatus":dt.casestatus,
            "infectionhistory":str(dt.infectionhistory),
            "infectiontype":dt.infectiontype,
            "distict":str(dt.distict.district_id),
            "public_id":str(dt.public.pubid),
            "pubname":dt.public.pubname,
            "pubcontact":dt.public.pubcontact,
            "pubemail":dt.public.pubemail,
            "kitrequest":str(dt.kitrequest.kitid),
            "categoryname":dt.kitrequest.category.categoryname,
            "casedate":dt.casedate
            }
        data.append(dv)
    print(data)
    return JsonResponse(data, safe=False)

def app_getShopList(request):
    t1=request.POST["t1"]
    data = dst.objects.get(district_id=t1)
    dataw=shp.objects.filter(distict=data,medstatus="approved").values("medshop_id","shopname","contactno","email","workinghrs")
    data=json.dumps(list(dataw))
    return HttpResponse(data, content_type="application/json")

def app_getShopList1(request):
    t1=request.POST["t1"]
    t2=request.POST["t2"]
    fd=stk.objects.filter(medicinename=t2).all()
    dl=[]
    for i in fd:
        dl.append(i.medshop.medshop_id)
    dl=list(set(dl))

    data = dst.objects.get(district_id=t1)
    dataw=shp.objects.filter(distict=data,medshop_id__in=dl).values("medshop_id","shopname","contactno","email","workinghrs")
    data=json.dumps(list(dataw))
    return HttpResponse(data, content_type="application/json")

def app_getMedicines(request):
    t1=request.POST["t1"] #medshopid
    data = shp.objects.get(medshop_id=t1)
    dataw=stk.objects.filter(medshop=data).values("stock_id","medicinename","medtype","medprice","medqty","meddec","medshop_id")
    data=json.dumps(list(dataw))
    return HttpResponse(data, content_type="application/json")

def app_addOrder(request):
    t1=request.POST["t1"] #log
    t2=request.POST["t2"] #qty
    t3=request.POST["t3"] #med
    t4=request.POST["t4"] #med
    today = date.today()

    log=usr.objects.get(login=t1)
    med=stk.objects.get(stock_id=t3)
    shop = shp.objects.get(medshop_id=t4)

    current = order.objects.filter(status="waiting",user=log,medshop=t4).count()
    if(current==0):
        order.objects.create(date=today,status="waiting",user=log,medshop=shop)
        id = order.objects.last()
    else:
        id = order.objects.get(status="waiting",user=log,medshop=t4)

    item.objects.create(qty=t2,order=id,stock=med)
    msg="ok"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_getCartItems(request):
    t1 = request.POST["t1"]
    d1 = usr.objects.get(login=t1)

    ordercount = order.objects.filter(user=d1,status="waiting").count()
    data = []
    if(ordercount > 0):
        corder = order.objects.filter(user=d1,status="waiting").all()
        for c in corder:
            dataw = item.objects.filter(order = c.orderid).all()

            for d in dataw:
                dv = {
                    "orderid":str(c.orderid),
                    "date":str(c.date),
                    "status":c.status,
                    "itemid":d.itemid,
                    "qty":d.qty,
                    "stock_id":str(d.stock),
                    "medicinename":d.stock.medicinename,
                    "medtype":d.stock.medtype,
                    "medprice":d.stock.medprice,
                    "medqty":d.stock.medqty,
                }
                data.append(dv)
    
    return JsonResponse(data, safe=False)

def app_userRemoveItem(request):
    t1 = request.POST["t1"]
    item.objects.filter(itemid=t1).delete()

    msg="ok"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_userPlaceOrder(request):
    t1 = request.POST["t1"]
    log = usr.objects.get(login = t1)
    
    t2 = request.POST["t2"] #holder
    t3 = request.POST["t3"] #cvv
    t4 = request.POST["t4"] #exp
    t5 = request.POST["t5"] #accno
    t6 = request.POST["t6"] #total
    today = date.today()

    flag = bank.objects.filter(holdername = t2, cvv = t3, accno = t5, exp = t4).count()
    
    if(flag == 1):
        cdata = bank.objects.get(holdername = t2, cvv = t3, accno = t5, exp = t4)
        if cdata.balance > int(t6) :

            bank.objects.filter(bankid=cdata.bankid).update(balance=int(cdata.balance)-int(t6))

            d1 = order.objects.filter(user=log,status="waiting").all()
            for da in d1:
                order.objects.filter(orderid=da.orderid).update(status="paid",date=today)
                d2 = item.objects.filter(order=da.orderid).all()
                for db in d2:
                    stk.objects.filter(stock_id=db.stock.stock_id).update(medqty=int(db.stock.medqty)-int(db.qty))
            msg="ok"
        else:
            msg="no"
    else:
        msg="invalid"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_userOrderDetails(request):
    t1 = request.POST['t1']
    d1 = usr.objects.get(login=t1)

    ordercount = order.objects.filter(~Q(status="waiting"),user=d1).count()
    print(ordercount)
    data = []
    if(ordercount > 0):
        corder = order.objects.filter(~Q(status="waiting"),user=d1).all()
        for i in corder:
            dataw = item.objects.filter(order = i.orderid).all()

            for d in dataw:
                dv = {
                        "orderid":str(i.orderid),
                    "date":str(i.date),
                    "status":i.status,
                    "itemid":d.itemid,
                    "qty":d.qty,
                    "stock_id":str(d.stock),
                    "medicinename":d.stock.medicinename,
                    "medtype":d.stock.medtype,
                    "medprice":d.stock.medprice,
                    "medqty":d.stock.medqty,
                }
                data.append(dv)
    return JsonResponse(data, safe=False)

def app_userSendServiceReq(request):
    t1 = request.POST['t1'] #log
    t2 = request.POST['t2'] #reason
    t3 = request.POST['t3'] #req
    t4 = request.POST['t4'] #date
    t5 = request.POST['t5'] #dist

    login = usr.objects.get(login=t1)
    dist = dst.objects.get(district_id=t5)
    today = date.today()

    sreq.objects.create(isolationreason=t2,requirement=t3,regdate=today,reqdate=t4,requeststatus="waiting",district=dist,public=login)
    msg="ok"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_userGetServiceBookings(request):
    t1 = request.POST['t1'] #log
    dat=log.objects.get(logid=t1)
    datar=usr.objects.get(login=dat)
    dataw=sreq.objects.filter(public=datar.pubid).all()
    
    db = []
    for d in dataw:
        if(d.requeststatus == "waiting" or d.requeststatus == "rejected"):
            worker = ""
            empname = ""
            empcontact = ""
            empemail = ""

        else:
            worker = d.worker.workerid
            empname = d.worker.empname
            empcontact = d.worker.empcontact
            empemail = d.worker.empemail

        v = {
            "serviceid":d.serviceid,
            "isolationreason":d.isolationreason,
            "requirement":d.requirement,
            "regdate":d.regdate,
            "reqdate":d.reqdate,
            "requeststatus":d.requeststatus,
            "district_id":str(d.district_id),
            "public_id":str(d.public.pubid),
            "worker_id":worker,
            "pubname":str(d.public.pubname),
            "pubcontact":str(d.public.pubcontact),
            "pubaddress":str(d.public.pubaddress),
            "empname":empname,
            "empcontact":empcontact,
            "empemail":empemail,
            }
        db.append(v)

    data=json.dumps(list(db))
    return HttpResponse(data, content_type="application/json")

def app_getAshaServiceRequests(request):
    t1 = request.POST['t1']
    dat=log.objects.get(logid=t1)
    datar=wrk.objects.get(login=dat)
    dataw=sreq.objects.filter(worker=datar.workerid,requeststatus="assigned").all()

    db = []
    for d in dataw:
        
        worker = d.worker.workerid
        empname = d.worker.empname
        empcontact = d.worker.empcontact
        empemail = d.worker.empemail

        v = {
            "serviceid":d.serviceid,
            "isolationreason":d.isolationreason,
            "requirement":d.requirement,
            "regdate":d.regdate,
            "reqdate":d.reqdate,
            "requeststatus":d.requeststatus,
            "district_id":str(d.district_id),
            "public_id":str(d.public.pubid),
            "worker_id":worker,
            "pubname":str(d.public.pubname),
            "pubcontact":str(d.public.pubcontact),
            "pubaddress":str(d.public.pubaddress),
            "empname":empname,
            "empcontact":empcontact,
            "empemail":empemail,
            }
        db.append(v)

    data=json.dumps(list(db))
    return HttpResponse(data, content_type="application/json")

def app_ashaCompleteService(request):
    t1 = request.POST['t1']
    sreq.objects.filter(serviceid=t1).update(requeststatus="completed")

    msg="ok"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_getAshaServiceHistory(request):
    t1 = request.POST['t1']
    dat=log.objects.get(logid=t1)
    datar=wrk.objects.get(login=dat)
    dataw=sreq.objects.filter(worker=datar.workerid,requeststatus="completed").all()

    db = []
    for d in dataw:
        
        worker = d.worker.workerid
        empname = d.worker.empname
        empcontact = d.worker.empcontact
        empemail = d.worker.empemail

        v = {
            "serviceid":d.serviceid,
            "isolationreason":d.isolationreason,
            "requirement":d.requirement,
            "regdate":d.regdate,
            "reqdate":d.reqdate,
            "requeststatus":d.requeststatus,
            "district_id":str(d.district_id),
            "public_id":str(d.public.pubid),
            "worker_id":worker,
            "pubname":str(d.public.pubname),
            "pubcontact":str(d.public.pubcontact),
            "pubaddress":str(d.public.pubaddress),
            "empname":empname,
            "empcontact":empcontact,
            "empemail":empemail,
            }
        db.append(v)

    data=json.dumps(list(db))
    return HttpResponse(data, content_type="application/json")

def app_getUserChat(request):
    t1 = request.POST['t1']
    t2 = request.POST['t2']
    d1 = usr.objects.get(login=t1)

    datar = chat.objects.filter(public=d1.pubid,doctor=t2).values("workertype","sendertype","message","senddate","sendtime","status")
    data=json.dumps(list(datar))
    return HttpResponse(data, content_type="application/json")

def app_getDocChat(request):
    t1 = request.POST['t1']
    t2 = request.POST['t2']
    d1 = wrk.objects.get(login=t1)

    datar = chat.objects.filter(public=t2,doctor=d1.workerid).values("workertype","sendertype","message","senddate","sendtime","status")
    data=json.dumps(list(datar))
    return HttpResponse(data, content_type="application/json")

def app_userSendMessage(request):
    t1 = request.POST['t1']
    t2 = request.POST['t2']
    t3 = request.POST['t3']

    d1 = usr.objects.get(login=t1)
    d2 = wrk.objects.get(workerid=t2)

    today = date.today()
    time = datetime.now().time()

    chat.objects.create(workertype="r",sendertype="s",message=t3,senddate=today,sendtime=time,status="sent",doctor=d2,public=d1)

    msg="ok"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_docSendMessage(request):
    t1 = request.POST['t1']
    t2 = request.POST['t2']
    t3 = request.POST['t3']

    d1 = wrk.objects.get(login=t1)
    d2 = usr.objects.get(pubid=t2)

    today = date.today()
    time = datetime.now().time()

    chat.objects.create(workertype="s",sendertype="r",message=t3,senddate=today,sendtime=time,status="sent",doctor=d1,public=d2)

    msg="ok"
    data=[{'result': msg}]
    return JsonResponse(data, safe=False)

def app_getTPR(request):
    t1 = request.POST['t1'] #dist
    today = date.today()
    total = cases.objects.filter(~Q(infectiontype="waiting" ),distict = t1,casedate = today).count()
    result = 0
    if total > 0:
        data = cases.objects.filter(infectiontype="Positive" ,distict = t1,casedate = today).count()
        if data > 0:
            result = (data/total)*100
    msg="ok:"+str('{0:.2f}'.format(result))
    data2=[{'result': msg}]
    return JsonResponse(data2, safe=False)