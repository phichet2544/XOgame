# XOgame
ตัวอย่างเกม XO

https://github.com/user-attachments/assets/273edb05-8555-41b6-9418-2badbdf0b7a3


มีการทำงาน
 - เลือกจำนวนช่องได้อิสระ
 - มี bot เล่นแทน Player ที่ 2
 - ระบบบันทึก
 - ดูประวัติการเล่นย้อนหลัง

1.หน้าจอหลัก ใช้วิธีการ ระบุจำนวนช่อง -> ส่งค่าไไหนหน้าเล่นเกม นำเลขที่ต้องการไปสร้างตาราง x*x และแสดงประวัติเรียกผ่าน Room

<img src="https://imgur.com/u5a4b6r.jpg" alt="รูปภาพ" width="250" height="500">

2.หน้าเล่นผู้เล่นจะเป็นเริ่มก่อนบอท จะมีการเช็คว่าใครชนะหรือเสมอ และเลือกจะเล่นเกมใหม่หรือบันทึกแล้วออก
 
<img src="https://imgur.com/v1tNodO.jpg" alt="รูปภาพ" width="250" height="500">
<img src="https://imgur.com/YWorNyk.jpg" alt="รูปภาพ" width="250" height="500">

3.การบันทึกค่า

การบันทึกจะทำการแปลงตารางเกมที่เป็น Matrix(2มิติ) ให้เป็นเป็น array 1 มิติ เพื่อง่ายต่อการจัดเก็บ

ช่อง select จะบันทึกว่าช่องไหนมีค่าเป็นผู้เล่นคนไหนเลือก 
  0=ไม่มีคนเลือก
1=ผู้เล่น 
2=บอท

ช่อง timeselect คือช่องระบุว่าลำดับการเลือกของผู้เล่นกับบอทว่าเลือกช่องไหน เช่น 9 คือเลือกช่อง(2,2) //*ช่องตาราง 0-2*// ในกรณีตาราง 3*3 
จะรุ้ได้อย่างไรว่าเกมนี้ใช้ช่องตารางเท่าไหร่ ?   ใช้วิธีการนับจำนวนช่อง select ว่ามีค่ากี่ตัวเช่น 9 ตัวนำไปถอด สแควรูทจะได้ 3 ช่อง
<img src="https://imgur.com/shC4GOF.jpg" alt="รูปภาพ" width="2000" height="300">

4.การสร้าง AI บอท

โดยรูปแบบจะเป็นการสุ่มเลือกเท่านั้น ไม่ได้เขียนให้มีการเป็น AI อัจฉริยะต้องการแต่อย่างใด โดยจะใช้วิธีตรวจสอบว่าช่องไม่มีใครเลือกหรือค่า=0 ลิสช่องมาว่ามีช่องไหนบ้างแล้วทำก่อนสุ่มเลือกมา 1 ช่อง

<img src="https://imgur.com/gJJt89F.jpg" alt="รูปภาพ" width="800" height="300">

5.การ Replay

ใช้วิธีเก็บค่า timeselect มาเปลี่ยนเทียบกับ select

<img src="https://imgur.com/rlwDcUA.jpg" alt="รูปภาพ" width="800" height="300">


