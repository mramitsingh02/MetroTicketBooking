insert into route (route_id,route_name,route_nick_name) values (100,'Red','Red Line');

 insert into station (station_id, distance,previous_station_id,next_station_id,route_id,station_name,station_nick_name)
              values
               (1001,3,null,1002,100,'Huda City Center','HCC'),
               (1002,4,1001,1003,100,'Iffco chowk','IFFCO'),
               (1003,5,1002,1004,100,'M.G. ROAD','M.G.ROAD'),
               (1004,2,1003,1005,100,'Sikanderpur','SIKANDERPUR'),
               (1005,3,1004,1006,100,'Guru dronacharya','GURUDRONACHARYA'),
               (1006,4,1005,1007,100,'Arjan garh','ARJANGARH'),
               (1007,5,1006,1008,100,'Ghitorni','GHITORNI'),
               (1008,3,1007,1009,100,'Sultanpur','SULTANPUR'),
               (1009,4,1008,1010,100,'Chhatarpur','CHHATARPUR'),
               (1010,7,1009,1011,100,'Qutab minar','QUTABMINAR'),
               (1011,2,1010,1012,100,'Saket','SAKET'),
               (1012,5,1011,1013,100,'Malviya nagar','MALVIYANAGAR'),
               (1013,3,1012,null,100,'Hauz khas','HAUZKHAS');