# Generated by Django 4.0.1 on 2022-03-20 07:10

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('savekerala', '0007_alter_notification_category'),
    ]

    operations = [
        migrations.AlterField(
            model_name='alerts',
            name='alertinstrction',
            field=models.CharField(max_length=100, verbose_name='alertinstrction'),
        ),
        migrations.AlterField(
            model_name='alerts',
            name='alertstatus',
            field=models.CharField(max_length=100, verbose_name='alertstatus'),
        ),
        migrations.AlterField(
            model_name='alerts',
            name='alerttype',
            field=models.CharField(max_length=100, verbose_name='alerttype'),
        ),
        migrations.AlterField(
            model_name='alerts',
            name='postdt',
            field=models.CharField(max_length=100, verbose_name='postdt'),
        ),
        migrations.AlterField(
            model_name='alerts',
            name='startdt',
            field=models.CharField(max_length=100, verbose_name='startdt'),
        ),
        migrations.AlterField(
            model_name='category',
            name='categorydesc',
            field=models.CharField(max_length=100, verbose_name='categorydesc'),
        ),
        migrations.AlterField(
            model_name='category',
            name='categoryname',
            field=models.CharField(max_length=100, verbose_name='categoryname'),
        ),
        migrations.AlterField(
            model_name='category',
            name='categoryprecotaions',
            field=models.CharField(max_length=100, verbose_name='categoryprecotaions'),
        ),
        migrations.AlterField(
            model_name='category',
            name='categorystartdt',
            field=models.CharField(max_length=100, verbose_name='categorystartdt'),
        ),
        migrations.AlterField(
            model_name='category',
            name='categorystatus',
            field=models.CharField(max_length=100, verbose_name='categorystatus'),
        ),
        migrations.AlterField(
            model_name='category',
            name='categorysymtoms',
            field=models.CharField(max_length=100, verbose_name='categorysymtoms'),
        ),
        migrations.AlterField(
            model_name='category',
            name='categorytype',
            field=models.CharField(max_length=100, verbose_name='categorytype'),
        ),
        migrations.AlterField(
            model_name='medicinestock',
            name='medpic',
            field=models.FileField(upload_to='images/', verbose_name='medpic'),
        ),
        migrations.AlterField(
            model_name='notification',
            name='notification',
            field=models.CharField(max_length=100, verbose_name='notification'),
        ),
        migrations.AlterField(
            model_name='notification',
            name='notificationtitle',
            field=models.CharField(max_length=100, verbose_name='notificationtitle'),
        ),
        migrations.AlterField(
            model_name='notification',
            name='notificdate',
            field=models.CharField(max_length=100, verbose_name='notificdate'),
        ),
    ]
