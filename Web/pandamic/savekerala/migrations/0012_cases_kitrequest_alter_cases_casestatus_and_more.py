# Generated by Django 4.0.3 on 2022-03-22 04:30

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('savekerala', '0011_alter_kitrequest_alotqrcode'),
    ]

    operations = [
        migrations.AddField(
            model_name='cases',
            name='kitrequest',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='savekerala.kitrequest'),
        ),
        migrations.AlterField(
            model_name='cases',
            name='casestatus',
            field=models.CharField(max_length=100, verbose_name='casestatus'),
        ),
        migrations.AlterField(
            model_name='cases',
            name='infectionhistory',
            field=models.FileField(upload_to='images/', verbose_name='infectionhistory'),
        ),
    ]
