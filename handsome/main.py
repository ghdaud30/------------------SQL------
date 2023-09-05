from sqlalchemy import create_engine
from sqlalchemy import(
    Column,
    Integer,
    String,
    Text,
    Float,
    Boolean
)
from sqlalchemy.orm import declarative_base, sessionmaker

Base = declarative_base()


class City(Base):

    __tablename__ = "city"

    id = Column(Integer, primary_key = True)
    name = Column(Text, nullable=False)
    countrycode = Column(String(3),nullable=False)
    district = Column(Text, nullable=False)
    population = Column(Integer, nullable = False)


        

engine = create_engine("sqlite:///world.sqlite",echo=True)
metadata = Base.metadata    
metadata.create_all(engine)
session = sessionmaker(bind=engine)()



with open("./raw/city_data.txt","r") as f:
    lines = f.readlines()

for line in lines:
    id, name, countrycode, district, population = tuple(line.strip().split("\t"))
    city = City(id =id, name =name, countrycode =countrycode,district= district, population= population)
    session.add(city)
    session.commit()
