INSERT INTO categories (idCategory, name) VALUES (1, 'Pet Care'), (2, 'Cooking'), (3, 'Housekeeping'), (4, 'Tutoring'), (5, 'Makeup'), (6, 'Free Time'), (7, 'People Care'), (8, 'Amusement');

INSERT INTO locations (idLocation, description, latitude, longitude) VALUES (1, 'Seattle, Waszyngton, Stany Zjednoczone', 47.6062095, -122.3320708), (2, 'San Francisco, Kalifornia, Stany Zjednoczone', 37.7749295, -122.41941550000001), (3, 'White Plains, Nowy Jork, Stany Zjednoczone', 41.03398620000001, -73.76290970000002);

# username: billgates
# password: 2aNJn29r
#
# username: stevejobs
# password: F7zKwGg2
#
# username:: markzuckerberg
# password: Zqq37aDT

INSERT INTO `users` (username, password, email, firstName, lastName, role, idLocation) VALUES ('billgates', '$2a$10$tjZR0.Hj4DExo1X81K0rf.RpD7e.bLSuMwoej5z8jAB6iB751TbcS','billgates@test.com', 'Bill', 'Gates', 'USER', 1), ('stevejobs', '$2a$10$s4Fsgj.U6Cql.3i7BD90VuDNWHwRW6G.gf3N781B47FrEcmXGk2NO','stevejobs@test.com', 'Steve', 'Jobs', 'USER', 2), ('markzuckerberg', '$2a$10$tgIejFFdFbHBL4yj/XMBu.Cy1yF0b5d0bcffY22SvOia8h60gEUWe','markzuckerberg@test.com', 'Mark', 'Zuckerberg', 'USER', 3);
