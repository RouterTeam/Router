import 'dart:core';

class StoryResult {
  List<Story> storys;
  Page page;
  StoryResult({this.page,this.storys});

  StoryResult.fromJson(Map<String, dynamic> json) {
    page = json['page'] != null ? new Page.fromJson(json['page']) : null;
    if (json['storys'] != null) {
      storys = new List<Story>();
      json['storys'].forEach((v) {
        storys.add(new Story.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
       if (this.page != null) {
          data['page'] = this.page.toJson();
        }
    if (this.storys != null) {
      data['storys'] = this.storys.map((v) => v.toJson()).toList();
    }
    return data;
  }

}



class Story {
  int id;
  String name;
  String content;
  String shortContent;
  int duration;
  String imgPath;
  String bigImg;
  int coverWidth;
  int coverHeight;
  String appFile;
  String webFile;
  int type;
  int serialStoryId;
  int secondSerialStoryId;
  int categoryId;
  int categoryNewId;
  int ageType;
  String categoryName;
  String categoryIntro;
  int isDel;
  Null isShowSerialIcon;
  int isFree;
  int status;
  int orderBy;
  int isPurchased;
  int isBuy;
  int isShow;
  int createTime;
  int onlineTime;
  int publishTime;
  String copyright;
  String translator;
  String author;
  String dubber;
  String illustrator;
  String producer;
  int ver;
  String language;
  Null labels;
  int magazineId;
  int isNow;
  int price;
  int parentId;
  int isParts;
  int scheduleType;
  Null onlineUrl;
  int wordCount;
  int vocabularyCount;
  Null isBuySerial;
  int readWordCount;
  Null isAbilityPlan;
  int refererGroupId;
  Null story;
  int productOrderTypeId;
  String shareUrl;
  int isAudio;
  int isNew;
  String bigCover;
  String appUrl;
  String cover;
  int isNew14;
  int isRecentUpdate;

  Story(
      {this.id,
        this.name,
        this.content,
        this.shortContent,
        this.duration,
        this.imgPath,
        this.bigImg,
        this.coverWidth,
        this.coverHeight,
        this.appFile,
        this.webFile,
        this.type,
        this.serialStoryId,
        this.secondSerialStoryId,
        this.categoryId,
        this.categoryNewId,
        this.ageType,
        this.categoryName,
        this.categoryIntro,
        this.isDel,
        this.isShowSerialIcon,
        this.isFree,
        this.status,
        this.orderBy,
        this.isPurchased,
        this.isBuy,
        this.isShow,
        this.createTime,
        this.onlineTime,
        this.publishTime,
        this.copyright,
        this.translator,
        this.author,
        this.dubber,
        this.illustrator,
        this.producer,
        this.ver,
        this.language,
        this.labels,
        this.magazineId,
        this.isNow,
        this.price,
        this.parentId,
        this.isParts,
        this.scheduleType,
        this.onlineUrl,
        this.wordCount,
        this.vocabularyCount,
        this.isBuySerial,
        this.readWordCount,
        this.isAbilityPlan,
        this.refererGroupId,
        this.story,
        this.productOrderTypeId,
        this.shareUrl,
        this.isAudio,
        this.isNew,
        this.bigCover,
        this.appUrl,
        this.cover,
        this.isNew14,
        this.isRecentUpdate});

  Story.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    name = json['name'];
    content = json['content'];
    shortContent = json['shortContent'];
    duration = json['duration'];
    imgPath = json['imgPath'];
    bigImg = json['bigImg'];
    coverWidth = json['coverWidth'];
    coverHeight = json['coverHeight'];
    appFile = json['appFile'];
    webFile = json['webFile'];
    type = json['type'];
    serialStoryId = json['serialStoryId'];
    secondSerialStoryId = json['secondSerialStoryId'];
    categoryId = json['categoryId'];
    categoryNewId = json['categoryNewId'];
    ageType = json['ageType'];
    categoryName = json['categoryName'];
    categoryIntro = json['categoryIntro'];
    isDel = json['isDel'];
    isShowSerialIcon = json['isShowSerialIcon'];
    isFree = json['isFree'];
    status = json['status'];
    orderBy = json['orderBy'];
    isPurchased = json['isPurchased'];
    isBuy = json['isBuy'];
    isShow = json['isShow'];
    createTime = json['createTime'];
    onlineTime = json['onlineTime'];
    publishTime = json['publishTime'];
    copyright = json['copyright'];
    translator = json['translator'];
    author = json['author'];
    dubber = json['dubber'];
    illustrator = json['illustrator'];
    producer = json['producer'];
    ver = json['ver'];
    language = json['language'];
    labels = json['labels'];
    magazineId = json['magazineId'];
    isNow = json['isNow'];
    price = json['price'];
    parentId = json['parentId'];
    isParts = json['isParts'];
    scheduleType = json['scheduleType'];
    onlineUrl = json['onlineUrl'];
    wordCount = json['wordCount'];
    vocabularyCount = json['vocabularyCount'];
    isBuySerial = json['isBuySerial'];
    readWordCount = json['readWordCount'];
    isAbilityPlan = json['isAbilityPlan'];
    refererGroupId = json['refererGroupId'];
    story = json['story'];
    productOrderTypeId = json['productOrderTypeId'];
    shareUrl = json['shareUrl'];
    isAudio = json['isAudio'];
    isNew = json['isNew'];
    bigCover = json['bigCover'];
    appUrl = json['appUrl'];
    cover = json['cover'];
    isNew14 = json['isNew14'];
    isRecentUpdate = json['isRecentUpdate'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['name'] = this.name;
    data['content'] = this.content;
    data['shortContent'] = this.shortContent;
    data['duration'] = this.duration;
    data['imgPath'] = this.imgPath;
    data['bigImg'] = this.bigImg;
    data['coverWidth'] = this.coverWidth;
    data['coverHeight'] = this.coverHeight;
    data['appFile'] = this.appFile;
    data['webFile'] = this.webFile;
    data['type'] = this.type;
    data['serialStoryId'] = this.serialStoryId;
    data['secondSerialStoryId'] = this.secondSerialStoryId;
    data['categoryId'] = this.categoryId;
    data['categoryNewId'] = this.categoryNewId;
    data['ageType'] = this.ageType;
    data['categoryName'] = this.categoryName;
    data['categoryIntro'] = this.categoryIntro;
    data['isDel'] = this.isDel;
    data['isShowSerialIcon'] = this.isShowSerialIcon;
    data['isFree'] = this.isFree;
    data['status'] = this.status;
    data['orderBy'] = this.orderBy;
    data['isPurchased'] = this.isPurchased;
    data['isBuy'] = this.isBuy;
    data['isShow'] = this.isShow;
    data['createTime'] = this.createTime;
    data['onlineTime'] = this.onlineTime;
    data['publishTime'] = this.publishTime;
    data['copyright'] = this.copyright;
    data['translator'] = this.translator;
    data['author'] = this.author;
    data['dubber'] = this.dubber;
    data['illustrator'] = this.illustrator;
    data['producer'] = this.producer;
    data['ver'] = this.ver;
    data['language'] = this.language;
    data['labels'] = this.labels;
    data['magazineId'] = this.magazineId;
    data['isNow'] = this.isNow;
    data['price'] = this.price;
    data['parentId'] = this.parentId;
    data['isParts'] = this.isParts;
    data['scheduleType'] = this.scheduleType;
    data['onlineUrl'] = this.onlineUrl;
    data['wordCount'] = this.wordCount;
    data['vocabularyCount'] = this.vocabularyCount;
    data['isBuySerial'] = this.isBuySerial;
    data['readWordCount'] = this.readWordCount;
    data['isAbilityPlan'] = this.isAbilityPlan;
    data['refererGroupId'] = this.refererGroupId;
    data['story'] = this.story;
    data['productOrderTypeId'] = this.productOrderTypeId;
    data['shareUrl'] = this.shareUrl;
    data['isAudio'] = this.isAudio;
    data['isNew'] = this.isNew;
    data['bigCover'] = this.bigCover;
    data['appUrl'] = this.appUrl;
    data['cover'] = this.cover;
    data['isNew14'] = this.isNew14;
    data['isRecentUpdate'] = this.isRecentUpdate;
    return data;
  }
}

class Page {
  int pageNo;
  int pageSize;
  int pageCount;
  bool hasNext;
  int rsCount;

  Page(
      {this.pageNo, this.pageSize, this.pageCount, this.hasNext, this.rsCount});

  Page.fromJson(Map<String, dynamic> json) {
    pageNo = json['pageNo'];
    pageSize = json['pageSize'];
    pageCount = json['pageCount'];
    hasNext = json['hasNext'];
    rsCount = json['rsCount'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['pageNo'] = this.pageNo;
    data['pageSize'] = this.pageSize;
    data['pageCount'] = this.pageCount;
    data['hasNext'] = this.hasNext;
    data['rsCount'] = this.rsCount;
    return data;
  }
}