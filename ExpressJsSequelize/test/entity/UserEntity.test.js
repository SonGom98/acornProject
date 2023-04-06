const userEntity=require("../../model/entity/UsersEntity")


describe("UserEntity Test",()=>{
    test("findAll", async()=>{
        const users=await userEntity.findAll();
        console.log(users)
    });
    test("findAll([u_id,pw,name])", async()=>{
        const users=await userEntity.findAll(
            {
                attributes:["u_id","pw","name"]
            }
        );
        console.log(users)
    });
    test("findAll(where:permission)", async()=>{
        const users=await userEntity.findAll(
            {
                where:{
                    permission:"admin"
                }
            }
        );
        console.log(users)
    });
    test("findAll(paging)", async ()=>{
        //ORM 과 페이징
        //SQL (쿼리)이 공통 규칙이 존재하는데 db 마다 조금씩 차이가 존재하고 특히 paging 과 함수가 많이 다르다.
        //ORM 이 데이터 베이스에 맞는 쿼리를 생성하기 때문에 db가 바껴도 커리 수정이 필요없다.

        //oracle  SELECT * FROM (SELECT *,ROWNUM r FROM (SELECT * FROM BOARD) b WHERE ROWNUM = 10) WHERE ROWNUM >= 5
        //mysql SELECT * FROM BOARD LIMIT 5,5
        //oracle null data 체크하는 함수 NVL(칼럼, 0)
        //mysql null data 체크하는 함수 IFNULL(칼럼, 0)

        const users=await userEntity.findAll({
            where : {permission:"USER"},
            offset : 0,
            limit : 5,
            order:[
                ["gender","ASC"],
                ["name","ASC"]
            ]
        })
        console.log(JSON.stringify(users,"usersEntity", 2))
    });
    test("findByPk", async()=>{
        const users=await userEntity.findByPk("user05");
        console.log(users)
    });

    test("create() 등록", async ()=>{
        const user={
                "u_id": "testUser03",
                "pw": "1234",
                "name": "비공개회원",
                "phone": "99999999993",
                "img_path": "/img/users/testUser02.jpeg",
                "email": "testUser03@gmail.com",
                "birth": "1988-03-03",
                "gender": "MALE",
                "address": "서울특별시",
                "detail_address": "마포구",
                "permission": "PRIVATE"
            }
            let result
            try{
                result = await userEntity.create(user);
            }catch (e){
                console.error(e)
            }
                console.log(result);
                //console.log(result._options.isNewRecord)
    });
    test("update 수정", async ()=>{
        const user={
            "name": "PRIVATE 회원",
            "phone": "19999999993",
            "img_path": "/img/users/PRIVATE회원.jpeg",
            "email": "테스트유저03@gmail.com",
            "birth": "1999-09-09",
            "gender": "FEMALE",
            "address": "경기도",
            "detail_address": "안양",
            "permission": "USER"
        }
        let result;
        try{
            result=await userEntity.update(user, {
            where : {u_id:"testUser03"}
        });
        }catch (e){
            console.error(e)
        }
        console.log(result);
    })

    test("destory 삭제", async ()=>{
       let result;
       try{
           result=await userEntity.destroy({
               where:{u_id:"testUser03"}
           });
       }catch (e) {
           console.error(e)
       }
        console.log(result)
    });
})