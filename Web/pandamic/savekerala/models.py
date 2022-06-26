from django.db import models
from django.db.models import Q
import datetime
from datetime import date as dt

# Create your models here.
class login(models.Model):
    logid = models.AutoField(primary_key=True)
    username = models.CharField("username",max_length=100)
    password =  models.CharField("password",max_length=100)
    role=models.CharField('role',max_length=10)
    #logid,username,password,role

class district(models.Model):
    district_id=models.AutoField(primary_key=True)
    district=models.CharField("district",max_length=100)
    districtadminnm=models.CharField("admin name",max_length=100)
    districtadmindes=models.CharField("admin designation",max_length=100)
    districtadmindcontact=models.CharField("admin contact",max_length=100)
    districtadmindemail=models.CharField("admin email",max_length=100)
    login=models.ForeignKey(login,on_delete=models.CASCADE,null=True)
    @property
    def getTPR(self):
        today = dt.today()
        total = cases.objects.filter(~Q(infectiontype="waiting" ),distict = self,casedate = today).count()
        result = 0
        if total > 0:
            data = cases.objects.filter(infectiontype="Positive" ,distict = self,casedate = today).count()
            if data > 0:
                result = (data/total)*100
        return str('{0:.2f}'.format(result))

    #district_id,district,districtadminnm,districtadmindes,districtadmindcontact,districtadmindemail,login

class locations(models.Model):
    location_id=models.AutoField(primary_key=True)
    location=models.CharField("location",max_length=100)
    distict=models.ForeignKey(district, on_delete=models.CASCADE, null=True)
    #location_id,location,distict

class category(models.Model):
    category_id=models.AutoField(primary_key=True)
    categoryname=models.CharField("categoryname",max_length=100)
    categorydesc=models.CharField("categorydesc",max_length=100)
    categorytype=models.CharField("categorytype",max_length=100)
    categorystartdt=models.CharField("categorystartdt",max_length=100)
    categorysymtoms=models.CharField("categorysymtoms",max_length=100)
    categoryprecotaions=models.CharField("categoryprecotaions",max_length=100)
    categorystatus=models.CharField("categorystatus",max_length=100)
  
    #category_id,categoryname,categorydesc,categorytype,categorystartdt,categorysymtoms,categoryprecotaions,categorystatus

class notification(models.Model):
    notif_id=models.AutoField(primary_key=True)
    notificationtitle=models.CharField("notificationtitle",max_length=100)
    notification=models.CharField("notification",max_length=100)
    notificdate=models.CharField("notificdate",max_length=100)
    category=models.ForeignKey(category, on_delete=models.CASCADE, null=True)
#notif_id,notificationtitle,notification,notificdate,category
class alerts(models.Model):
    alertsid=models.AutoField(primary_key=True)
    alerttype=models.CharField("alerttype",max_length=100)
    alertinstrction=models.CharField("alertinstrction",max_length=100)
    category=models.ForeignKey(category, on_delete=models.CASCADE, null=True)
    distict=models.ForeignKey(district, on_delete=models.CASCADE, null=True)
    postdt=models.CharField("postdt",max_length=100)
    startdt=models.CharField("startdt",max_length=100)
    alertstatus=models.CharField("alertstatus",max_length=100)
    #alertsid,alerttype,alertinstrction,category,distict,postdt,startdt,alertstatus

class medicalshop(models.Model):
    medshop_id=models.AutoField(primary_key=True)
    shopname=models.CharField("shopname",max_length=100)
    licenseno=models.CharField("licenseno",max_length=100)
    ownerno=models.CharField("ownerno",max_length=100)
    contactno=models.CharField("contactno",max_length=100)
    email=models.CharField("email",max_length=100)
    workinghrs=models.CharField("workinghrs",max_length=100)
    location=models.ForeignKey(locations, on_delete=models.CASCADE, null=True)
    licensecpy=models.FileField("licensecpy",max_length=100,upload_to="medshop/")
    medstatus=models.CharField("medstatus",max_length=100)
    login=models.ForeignKey(login,on_delete=models.CASCADE,null=True)
    distict=models.ForeignKey(district, on_delete=models.CASCADE, null=True)
#medshop_id,shopname,licenseno,ownerno,contactno,email,workinghrs,location,licensecpy,medstatus,login,distict

class medicinestock(models.Model):
    stock_id=models.AutoField(primary_key=True)
    medshop=models.ForeignKey(medicalshop, on_delete=models.CASCADE, null=True)
    medicinename=models.CharField("medicinename",max_length=100)
    medtype=models.CharField("medtype",max_length=100)
    medprice=models.CharField("medprice",max_length=100)
    medqty=models.CharField("medqty",max_length=100)
    meddec=models.CharField("meddec",max_length=100)
    medpic=models.FileField("medpic",max_length=100,upload_to="images/")
    #stock_id ,medshop,medicinename,medtype,medprice,medqty,meddec,medpic

class worker(models.Model):
    workerid=models.AutoField(primary_key=True)
    empcode=models.CharField("empcode",max_length=100)
    location=models.ForeignKey(locations,on_delete=models.CASCADE,null=True)
    distict=models.ForeignKey(district,on_delete=models.CASCADE,null=True)
    empname=models.CharField("empname",max_length=100)
    empphoto=models.FileField("empphoto",max_length=100,upload_to="images/")
    empcontact=models.CharField("empcontact",max_length=100)
    empemail=models.CharField("empemail",max_length=100)
    empstatus=models.CharField("empstatus",max_length=100)
    empaddress=models.CharField("empaddress",max_length=100)
    emptype=models.CharField("type",max_length=100)
    login=models.ForeignKey(login,on_delete=models.CASCADE,null=True)

#workerid,empcode,location,distict,empname,empphoto,empcontact,empemail,empstatus,empaddress,emptype,login
class public(models.Model):
    pubid=models.AutoField(primary_key=True)
    pubname=models.CharField("pubname",max_length=100)
    addharno=models.CharField("addharno",max_length=100)
    addharcopy=models.FileField("addharcopy",max_length=100,upload_to="aadhaar/")
    pubdob=models.CharField("pubdob",max_length=100)
    pubcontact=models.CharField("pubcontact",max_length=100)
    pubemail=models.CharField("pubemail",max_length=100)
    pubaddress=models.CharField("pubaddress",max_length=100)
    pubjob=models.CharField("pubjob",max_length=100)
    pubjobsector=models.CharField("pubjobsector",max_length=100)
    pubjobdescrp=models.CharField("pubjobdescrp",max_length=100)
    pubphoto=models.FileField("pubphoto",max_length=100,upload_to="photo/")
    pubstatus=models.CharField("pubstatus",max_length=100)
    location=models.ForeignKey(locations,on_delete=models.CASCADE,null=True)
    distict=models.ForeignKey(district,on_delete=models.CASCADE,null=True)
    login=models.ForeignKey(login,on_delete=models.CASCADE,null=True)
#pubid,pubname,addharno,addharcopy,pubdob,pubcontact,pubemail,pubaddress,pubjob,pubjobsector,pubjobdescrp,pubphoto,pubstatus,location,distict,login
class kitrequest(models.Model):
    kitid=models.AutoField(primary_key=True)
    public=models.ForeignKey(public,on_delete=models.CASCADE,null=True)
    kitstaus=models.CharField("kitstaus",max_length=100)
    requestdt=models.CharField("requestdt",max_length=100)
    previousrequestdate=models.CharField("previousrequestdate",max_length=100)
    category = models.ForeignKey(category,on_delete=models.CASCADE,null=True)
    alotqrcode=models.CharField("alotqrcode",max_length=100)
    ashaworker=models.ForeignKey(worker,on_delete=models.CASCADE,null=True)
    workstatus=models.CharField("workstatus",max_length=100)
    allotedate=models.CharField("allotedate",max_length=100)
    distict=models.ForeignKey(district, on_delete=models.CASCADE, null=True)
    @property
    def getcasedet(self):
        return cases.objects.get(kitrequest=self)



class cases(models.Model):
    caseid=models.AutoField(primary_key=True)
    public=models.ForeignKey(public,on_delete=models.CASCADE,null=True)
    distict=models.ForeignKey(district,on_delete=models.CASCADE,null=True)
    kitrequest=models.ForeignKey(kitrequest,on_delete=models.CASCADE,null=True)
    casestatus=models.CharField("casestatus",max_length=100)
    infectionhistory=models.FileField("infectionhistory",max_length=100,upload_to="images/")
    infectiontype=models.CharField("allotedate",max_length=100)
    casedate = models.CharField("casedate",max_length=100,default='')
    @property
    def getassistancedet(self):

        return assisatnce.objects.get(cases=self)

class assisatnce(models.Model):
    assitid=models.AutoField(primary_key=True)
    cases=models.ForeignKey(cases,on_delete=models.CASCADE,null=True)
    doctor=models.ForeignKey(worker,on_delete=models.CASCADE,null=True)
    date=models.CharField("date",max_length=100)
    message=models.CharField("message",max_length=100)

class servicerequest(models.Model):
    serviceid=models.AutoField(primary_key=True)
    public=models.ForeignKey(public,on_delete=models.CASCADE,null=True)
    district=models.ForeignKey(district,on_delete=models.CASCADE,null=True)
    worker = models.ForeignKey(worker,on_delete=models.CASCADE,null=True)
    isolationreason=models.CharField("isolationreason",max_length=100)
    requirement=models.CharField("requirement",max_length=100)
    regdate=models.CharField("regdate",max_length=100)
    reqdate=models.CharField("reqdate",max_length=100)
    requeststatus=models.CharField("status",max_length=100)
    

class allotedwork(models.Model):
    allotid=models.AutoField(primary_key=True)
    servicerequest=models.ForeignKey(servicerequest,on_delete=models.CASCADE,null=True)
    ashaworker=models.ForeignKey(worker,on_delete=models.CASCADE,null=True)
    workstatus=models.CharField("workstatus",max_length=100)
    allotedate=models.CharField("allotedate",max_length=100)
    report=models.CharField("report",max_length=100)

class chat(models.Model):
    docchatid=models.AutoField(primary_key=True)
    doctor=models.ForeignKey(worker,on_delete=models.CASCADE,null=True)
    public=models.ForeignKey(public,on_delete=models.CASCADE,null=True)
    workertype=models.CharField("sendertype",max_length=100)
    sendertype=models.CharField("sendertype",max_length=100)
    message=models.CharField("message",max_length=1000)
    senddate=models.CharField("senddate",max_length=100)
    sendtime=models.CharField("sendtime",max_length=100)
    status=models.CharField("status",max_length=100)

class order(models.Model):
    orderid=models.AutoField(primary_key=True)
    user=models.ForeignKey(public,on_delete=models.CASCADE,null=True)
    medshop= models.ForeignKey(medicalshop,on_delete=models.CASCADE,null=True)
    date=models.CharField("date",max_length=100)
    status=models.CharField("status",max_length=100)
    @property
    def getOrderItems(self):
        return item.objects.filter(order=self).all()
    @property
    def getOrderSum(self):
        data =item.objects.filter(order=self).all()
        total = 0
        for d in data:
            total += (int(d.qty)*int(d.stock.medprice))
        return total

class item(models.Model):
    itemid = models.AutoField(primary_key=True)
    stock = models.ForeignKey(medicinestock,on_delete=models.CASCADE,null=True)
    qty = models.IntegerField()
    order = models.ForeignKey(order,on_delete=models.CASCADE,null=True)
    
class bank(models.Model):
    bankid = models.AutoField(primary_key=True)
    holdername = models.CharField("holdername",max_length=100)
    cvv = models.CharField("cvv",max_length=10)
    accno = models.CharField("accno",max_length=50)
    exp = models.CharField("exp",max_length=50)
    balance = models.IntegerField()