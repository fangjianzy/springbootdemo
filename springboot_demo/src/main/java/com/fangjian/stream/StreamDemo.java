package com.fangjian.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**  
* Stream jdk1.8新特性demo
* @author Jimmy.Fang
*/
public class StreamDemo {
	
	public static void main(String[] args) {
		
		StreamDemo service = new StreamDemo();
		List<StudentsDTO> students = service.getStudentsDTO();
		List<TeacherDTO> teachers = service.getTeachers();
		
		//代替join
		students.stream().forEach(
				user -> {
					TeacherDTO teacher = teachers.stream().filter(t -> t.gettId()== user.gettId()).findFirst().orElse(null);
					if(teacher != null) {
						user.settName(teacher.gettName());
					}else {
						user.settName(null);
					}
				}
		);
		students.stream().forEach(user -> System.out.println(user.toString()));
		//重新组装list
		List<String> userIdList = students.stream().map(StudentsDTO::getsName).collect(Collectors.toList());
		
		//过滤
		List<StudentsDTO> userList1 = students.stream().filter(user -> "刘德华".equals(user.getsName())).collect(Collectors.toList());
		userList1.forEach(user -> System.out.print(user.getsName() + '、'));
		//distinct 去重复
		List<Integer> userAgeList = students.stream().map(StudentsDTO::getAge).distinct().collect(Collectors.toList());
		//limit分页
		List<StudentsDTO> userList3 = students.stream().filter(user -> user.getAge() % 2 == 0).limit(2).collect(Collectors.toList());
		userList3.forEach(user -> System.out.print(user.getsName() + '、'));
		//排序
		List<StudentsDTO> userList4 = students.stream().sorted((s1,s2) -> s2.getAge() - s1.getAge()).collect(Collectors.toList());
		userList4.forEach(user -> System.out.print(user.getsName() + '、'));
		//跳过n个元素后再输出
		System.out.println("跳过前面两个user的其他所有user");
		List<StudentsDTO> userList5 = students.stream().skip(2).collect(Collectors.toList());
		userList5.forEach(user -> System.out.print(user.getsName() + '、'));
		//映射
		System.out.println("姓名是xx的同学");
		List<String> userList6 = students.stream().filter(user -> "刘德华".equals(user.getsName())).map(StudentsDTO::getsName).collect(Collectors.toList());
		userList6.forEach(user -> System.out.print(user + '、'));
		//求和1
		int userList7 = students.stream().filter(user -> "刘德华".equals(user.getsName())).mapToInt(StudentsDTO::getAge).sum();
		//求和2
		BigDecimal userList70 = students.stream().filter(user -> "刘德华".equals(user.getsName())).map(StudentsDTO::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		//查找-allMatch,用于检测是否全部都满足指定的参数行为，如果全部满足则返回true，例如我们判断是否所有的user年龄都大于9岁，实现如下：
		Boolean b = students.stream().allMatch(user -> user.getAge() >9);
		//anyMatch,anyMatch则是检测是否存在一个或多个满足指定的参数行为，如果满足则返回true，例如判断是否有user的年龄大于15岁，实现如下：
		Boolean bo = students.stream().anyMatch(user -> user.getAge() >15);
		//noneMatch　　noneMatch用于检测是否不存在满足指定行为的元素，如果不存在则返回true，例如判断是否不存在年龄是15岁的user，实现如下
		Boolean boo = students.stream().noneMatch(user -> user.getAge() == 15);
		// findFirst,findFirst用于返回满足条件的第一个元素，比如返回年龄大于12岁的user中的第一个，实现如下:
		Optional<StudentsDTO> first = students.stream().filter(u -> u.getAge() > 10).findFirst();
		StudentsDTO user = first.get();
		//findAny,findAny相对于findFirst的区别在于，findAny不一定返回第一个，而是返回任意一个，比如返回年龄大于12岁的user中的任意一个：
		Optional<StudentsDTO> anyOne = students.stream().filter(u -> u.getAge() > 10).findAny();
		StudentsDTO user2 = anyOne.get();
		
		//reduce,计算
		Integer ages2 = students.stream().filter(student -> "清华大学".equals(student.getsName())).map(StudentsDTO::getAge).reduce(0,(a,c)->a+c);
		Integer ages3 = students.stream().filter(student -> "清华大学".equals(student.getsName())).map(StudentsDTO::getAge).reduce(0,Integer::sum);
		Integer ages4 = students.stream().filter(student -> "清华大学".equals(student.getsName())).map(StudentsDTO::getAge).reduce(Integer::sum).get();
		
		//counting 计算个数
		long COUNT  = students.stream().count();//简化版本
		long COUNT2 = students.stream().collect(Collectors.counting());//原始版本
		//maxBy、minBy
		System.out.println("user的年龄最大值和最小值");
		Integer maxAge =students.stream().collect(Collectors.maxBy((s1, s2) -> s1.getAge() - s2.getAge())).get().getAge();
		Integer maxAge2 = students.stream().collect(Collectors.maxBy(Comparator.comparing(StudentsDTO::getAge))).get().getAge();
		Integer minAge = students.stream().collect(Collectors.minBy((S1,S2) -> S1.getAge()- S2.getAge())).get().getAge();
		Integer minAge2 = students.stream().collect(Collectors.minBy(Comparator.comparing(StudentsDTO::getAge))).get().getAge();
		//求和
		Integer sumAge =students.stream().collect(Collectors.summingInt(StudentsDTO::getAge));
		//平均值averageInt、averageLong、averageDouble
		double averageAge = students.stream().collect(Collectors.averagingDouble(StudentsDTO::getAge));
		//一次性查询元素个数、总和、最大值、最小值和平均值（summarizingInt、summarizingLong、summarizingDouble）
		IntSummaryStatistics summaryStatistics = students.stream().collect(Collectors.summarizingInt(StudentsDTO::getAge));
		//summaryStatistics = IntSummaryStatistics{count=10, sum=145, min=10, average=14.500000, max=25}
		
	}
	
	
	public List<TeacherDTO> getTeachers(){
		List<TeacherDTO> list = Arrays.asList(
			      new TeacherDTO(1, "张三老师"),
			      new TeacherDTO(2, "李四老师"),
			      new TeacherDTO(3, "王五老师"),
			      new TeacherDTO(4, "赵六老师")
		);
		return list;
	}
	public List<StudentsDTO> getStudentsDTO(){
		List<StudentsDTO> list = Arrays.asList(
			      new StudentsDTO(11,"刘德华", 1),
			      new StudentsDTO(12,"张旭阳", 1),
			      new StudentsDTO(13,"张学友", 2),
			      new StudentsDTO(14,"科比", 2),
			      new StudentsDTO(15,"张思雨", 3),
			      new StudentsDTO(16,"张燕", 3),
			      new StudentsDTO(17,"易建联", 4)
			  );
		return list;
	}
}
