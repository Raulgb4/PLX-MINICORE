int x;
int y;
int z;
select last x from 1 to 10 
   select y from 1 to 10 
      select z from 1 to 10 
         where ( x+y+z == x*y*z);
print(x);
print(y);
print(z);

