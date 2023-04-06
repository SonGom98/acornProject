const sequelize =require("../webAppBoardSequelize");
const boardsEntity=require("../entity/BoardsEntity")(sequelize);
const usersEntity = require("../entity/UsersEntity")(sequelize);
const boardsRepliesEntity = require("../entity/BoardsRepliesEntity")(sequelize);
const PageVo= require("../vo/PageVo");

const {where} = require("sequelize");


class BoardService{
    async detail(bId){
        //boardsRepliesEntity 를 만들어서 BoardService.detail 을 호출할때 리플 리스트를 지연 로딩 구현하라

        // Boards : BoardsReplies = 1 : N (hasmany)
        boardsEntity.hasMany(boardsRepliesEntity,{
          foreignKey : "b_id", //board_replies 가 참조하는 boards 의 외래키
          as:"replies",
            //where:{parent_br_id:null} //대댓글은 제외가 안됨
        });
        //지연로딩은 조건을 줄 수 없다.

        //Boards : Users = N : 1 (belongsTo)
        boardsEntity.belongsTo(usersEntity,{
            foreignKey : "u_id", //users 가 참조하는 boards 의 외래키
            as: "user" //JOIN or 지연로딩일때 user를 가져왔을 때 board 에 생성되는 필드
        })
        //findOne : 무조건 1개의 결과를 반환
        const board = await boardsEntity.findOne({
            where:{
                b_id:bId
            },
            // include:[
            //     {
            //         medel:boardRepliesEntity,
            //         as : "replies",
            //         required:false, // 뎃글이 없는 게시글도 출력 (left join)
            //         where : {parent_br_id:null}
            //     }
            // ]

            // include 옵션을 사용하면 eager loading(즉시로딩) 조인으로 user를 불러온다.
            // include:[
            //     {
            //         model:usersEntity,
            //         as:"user",
            //         //required: true, //true = innerJoin, false = leftJoin
            //         //attributes:["email", "name"]
            //     }
            // ]
        });
        return board;
    }


    async list(reqParams){
        const whereObj={}
        const orderArr=[]

        if(reqParams.field && reqParams.value){
            whereObj[reqParams.field]= reqParams.value;
        }//{"status":"PUBLIC"}

        if(reqParams.orderField && reqParams.orderDirect){
            orderArr.push(reqParams.orderField);
            orderArr.push(reqParams.orderDirect)
        }


        const totalCnt = await boardsEntity.count({
            where:whereObj
        })

        const pageVo=new PageVo(reqParams.page, totalCnt, reqParams, 3);

        const boards=await boardsEntity.findAll({
            offset:pageVo.offset,
            limit:pageVo.rowLength,
            where:whereObj,
            order:[orderArr]
        })
        boards.pageVo=pageVo;
        console.log(JSON.stringify(pageVo.totalRow))
        return boards;
    }

}
module.exports = BoardService;