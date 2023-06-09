const{Sequelize,DataTypes} = require("sequelize");

module.exports=(sequelize)=>{
    const boardRepliesEntity = sequelize.define("boardRepliesEntity", {
        br_id:{
            type: DataTypes.INTEGER.UNSIGNED,
            primaryKey:true,
            autoIncrement:true
        },
        b_id:{
            type: DataTypes.INTEGER.UNSIGNED,
            allowNull: false,
            primaryKey:true,
            references:{
                model:"boardEntity",
                key:"b_id",
                onDelete: "CASCADE",
                onUpdate: "CASCADE"
            }
        },
        u_id:{
            type: DataTypes.STRING(255),
            allowNull: false,
            references:{
                model:"usersEntity",
                key:"u_id",
                onDelete: "CASCADE",
                onUpdate: "CASCADE"
            }
        },
        parent_br_id:{
            type: DataTypes.INTEGER.UNSIGNED,
            allowNull: false,
            references:{
                model:"boardRepliesEntity",
                key:"br_id",
                onDelete: "CASCADE",
                onUpdate: "CASCADE"

            }
        },
        post_time:{
            type: DataTypes.DATE,
            defaultValue: Sequelize.literal("CURRENT_TIMESTAMP")
        },
        update_time: {
            type: DataTypes.DATE,
            defaultValue: Sequelize.literal("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
        },
        status: {
            type: DataTypes.ENUM("PUBLIC", "PRIVATE", "REPORT", "BLOCK"),
            defaultValue: "PUBLIC"
        },
        img_path:{
            type:DataTypes.STRING(20),
        },
        content: {
            type: DataTypes.TEXT,
            allowNull: false
        }
    },{
        tableName: "board_replies",
        timestamps:false
    })
    return boardRepliesEntity;
}