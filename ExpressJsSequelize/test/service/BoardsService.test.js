const BoardService = require("../../model/service/BoardService");
const boardsService = new BoardService();

describe("BoardsService test",()=>{
    test("list",async ()=>{
        //let page=1;
        let reqParams={
          field:"status", //검색할 칼럼
          value:"PUBLIC", //검색할 값
          page:"1",
            orderField:"b_id",
            orderDirect:"DESC"
        };
        //const pageVo=new PageVo();


        const boards=await boardsService.list(reqParams);
        console.log(JSON.stringify(boards,"boardsEntity,2"))
    })

    test("detail join 테스트", async ()=>{
        const board = await boardsService.detail(6);
        console.log(JSON.stringify(board, "boardEntity",2));
        //user 를 호출할 때 Lazy Loading 지연로딩이 발생 (게으른 연산)
        //필요할때만 조회하기 때문에 데이터 낭비가 없다.
        //단점 : join 보다 느리다.
        //const user =await board.getUser();
        const replies = await board.getReplies();
        //console.log(user);
        console.log(JSON.stringify(replies,"boardRepliesEntity", 2))
    })
})