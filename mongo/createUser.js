// To be used only with mongos >= 4.0.27 from /docker-entrypoint-initdb.d folder
db = db.getSiblingDB("phones");
db.createUser({ "user": "admin", pwd: "4dm1n", roles: [] });
